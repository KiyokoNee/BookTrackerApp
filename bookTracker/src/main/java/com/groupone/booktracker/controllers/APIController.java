package com.groupone.booktracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api")
public class APIController {
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/search")
	public String search(Model model, @RequestParam("bookQuery") String bookQuery, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("bookQuery", bookQuery);
		
		return "redirect:/";
	}
}
