package com.groupone.booktracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupone.booktracker.dtos.BookDetailsDTO;
import com.groupone.booktracker.dtos.SearchBookDocDTO;
import com.groupone.booktracker.services.APIService;

@Controller
@RequestMapping("/api")
public class APIController {
	@Autowired
	private APIService apiServ;
	
	@PostMapping("/search")
	public String search(Model model, @RequestParam("bookQuery") String bookQuery, RedirectAttributes redirectAttributes) {
		String adjustedQuery = apiServ.convertQuery(bookQuery);
		String uri = "https://openlibrary.org/search.json?q=" + adjustedQuery;
		List<SearchBookDocDTO> books = apiServ.findByQuery(bookQuery);
		
		redirectAttributes.addFlashAttribute("adjustedQuery", adjustedQuery);
		redirectAttributes.addFlashAttribute("uriCall", uri);
		redirectAttributes.addFlashAttribute("books", books);
		redirectAttributes.addFlashAttribute("cleanKey", apiServ.cleanKey(books.get(0).getKey()));
		
		return "redirect:/";
	}
	
	@PostMapping("/")
	public String test(Model model, @RequestParam("bookKey") String bookKey, RedirectAttributes redirectAttributes) {
		BookDetailsDTO result = apiServ.findByKey(bookKey);
		List<String> authors = apiServ.getAuthorNames(result);
		String img = apiServ.getImageURLByKey(bookKey);
		redirectAttributes.addFlashAttribute("book", result);
		redirectAttributes.addFlashAttribute("imgURL", img);
		redirectAttributes.addFlashAttribute("authors", authors);
		
		
		return "redirect:/";
	}
}
