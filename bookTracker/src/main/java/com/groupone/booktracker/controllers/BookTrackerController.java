package com.groupone.booktracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookTrackerController {
	
	// Will remove later, using for testing purposes
	@GetMapping("/test")
	public String test() {
		return "index.jsp";
	}

	// Get requests - In Progress
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
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "index.jsp";
	}
	
	@GetMapping("/mybooks")
	public String myBooks() {
		return "index.jsp";
	}
	
	@GetMapping("/book/{key}")
	public String bookDetails() {
		return "index.jsp";
	}
	
	@GetMapping("/book/{key}/edit")
	public String editBookForm() {
		return "index.jsp";
	}
	
	// Post Requests - In Progress
	@PostMapping("/login")
	public String login() {
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register() {
		return "redirect:/";
	}
	
	@PostMapping("/borrow/{key}")
	public String borrow() {
		return "redirect:/";
	}
	
	// Put Requests - In Progress
	@PutMapping("/book/edit")
	public String editBorrow() {
		return "redirect:/";
	}
	
	// Delete Requests - In Progress
	@DeleteMapping("/book/{key}/delete")
	public String deleteBorrow() {
		return "redirect:/";
	}
	
}
