package com.groupone.booktracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		redirectAttributes.addFlashAttribute("adjustedQuery", adjustedQuery);
		redirectAttributes.addFlashAttribute("uriCall", uri);
		redirectAttributes.addFlashAttribute("books", apiServ.findByQuery(bookQuery));
		
		return "redirect:/";
	}
}
