package com.groupone.booktracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookTrackerController {
	
	// Will remove later, using for testing purposes
	@GetMapping("/test")
	public String test() {
		return "index.jsp";
	}

	@GetMapping
	public String home() {
		
		return "index.jsp";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "index.jsp";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "index.jsp";
	}
}
