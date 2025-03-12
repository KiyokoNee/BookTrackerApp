package com.groupone.booktracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groupone.booktracker.dtos.BookDetailsDTO;
import com.groupone.booktracker.dtos.SearchBookDocDTO;
import com.groupone.booktracker.models.Book;
import com.groupone.booktracker.models.LoginUser;
import com.groupone.booktracker.models.User;
import com.groupone.booktracker.services.APIService;
import com.groupone.booktracker.services.BookService;
import com.groupone.booktracker.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class BookTrackerController {
	
	@Autowired
	private APIService apiServ;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private HttpSession session;

	// Get requests - In Progress
	
	@GetMapping()
	public String home() {
		if( !checkLogin() ) { return "redirect:/dashboard"; }
		
		return "home.jsp";
	}
	
	@GetMapping("/search")
	public String searchHome(
			@RequestParam("query") String query,
			Model view
			) {
		String adjustedQuery = apiServ.convertQuery(query);
		session.setAttribute("searchQuery", adjustedQuery);
		String uri = "https://openlibrary.org/search.json?q=" + adjustedQuery;
		List<SearchBookDocDTO> books = apiServ.findByQuery(query);
		
		view.addAttribute("books", books);
		
		return checkLogin() ? "home.jsp" : "dashboard.jsp";
	}
	
	@GetMapping("/book/{bookKey}/details")
	public String bookDetails(
			@PathVariable String bookKey,
			Model view
			) {
		
		Book potentialBook = bookService.getBookByKey(bookKey);
		if( potentialBook != null) {
			view.addAttribute("potentialBook", potentialBook);
		}
		
		BookDetailsDTO result = apiServ.findByKey(bookKey);
		List<String> authors = apiServ.getAuthorNames(result);
		String img = apiServ.getImageURLByKey(bookKey);
		view.addAttribute("book", result);
		view.addAttribute("imgURL", img);
		view.addAttribute("authors", authors);
		
		return "bookDetails.jsp";
	}
	
	@GetMapping("/register")
	public String registerForm(
			@ModelAttribute("newUser") User newUser
			) {
		if( !checkLogin() ) { return "redirect:/dashboard"; }
		
		return "registration.jsp";
	}
	
	@GetMapping("/login")
	public String loginForm(
			@ModelAttribute("loginUser") LoginUser loginUser
			) {
		if( !checkLogin() ) { return "redirect:/dashboard"; }
		
		return "login.jsp";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		
		if( checkLogin() ) { return "redirect:/login"; }
		return "dashboard.jsp";
	}
	
	@GetMapping("/mybooks")
	public String myBooks(
			Model view
			) {
		if( checkLogin() ) { return "redirect:/login"; }
		view.addAttribute("borrowedBooks", bookService.getBooksByBorrowerId( (Long) session.getAttribute("loggedInUser") ));
		
		return "mybooks.jsp";
	}
	
	@GetMapping("/book/{bookKey}/edit")
	public String editBookForm(
			@PathVariable String bookKey,
			Model view
			) {
		
		if( checkLogin() ) { return "redirect:/login"; }
		
		Book oldBook = bookService.getBookByKey(bookKey);
		if( oldBook.getBorrower().getId() != (long) session.getAttribute("loggedInUser")  ) {
			return "redirect:/dashboard";
		}
		String img = apiServ.getImageURLByKey(bookKey);
		
		view.addAttribute("oldBook", oldBook);
		view.addAttribute("imgURL", img);
		view.addAttribute("authors", oldBook.getAuthors());
		
		return "editBook.jsp";
	}
	
	
//	POST REQUESTS
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result
			) {
		User newRegisteredUser = userService.register(newUser, result);
		if(  newRegisteredUser == null || result.hasErrors() ) {
			return "registration.jsp";
		}
		session.setAttribute("loggedInUser", newRegisteredUser.getId() );
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("loginUser") LoginUser loginUser,
			BindingResult result
			) {
		User maybeUser = userService.login(loginUser, result);
		if( result.hasErrors() ) {
			return "login.jsp";
		}
		session.setAttribute("loggedInUser", maybeUser.getId() );
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/borrow/{bookKey}")
	public String borrow(
			@PathVariable String bookKey
			) {
		
		if( checkLogin() ) { return "redirect:/login"; }
		
		User borrower = userService.findById( (Long) session.getAttribute("loggedInUser") );

		bookService.borrowBookByKey(bookKey, borrower);
		
		return "redirect:/mybooks";
	}

	
	@PostMapping("/logout")
	public String logout() {
		
		session.setAttribute("loggedInUser", null);
		return "redirect:/"; 
	}	
	
	
//	PUT REQUESTS
	
	@PutMapping("/book/{bookKey}/edit")
	public String editBorrow(
			@PathVariable String bookKey,
			@Valid @ModelAttribute("oldBook") Book oldBook,
			BindingResult result,
			Model view 
			) {
		
		if( checkLogin() ) { return "redirect:/login"; }
		
		Book newBook = bookService.getBookByKey(bookKey);
		if( newBook.getBorrower().getId() != (long) session.getAttribute("loggedInUser")  ) {
			return "redirect:/dashboard";
		}
		
		if(result.hasErrors()) {
			view.addAttribute("oldBook", oldBook);
			return "editBook.jsp";
		}
		
		newBook.setPagesRead(oldBook.getPagesRead());
		newBook.setReturnBy(oldBook.getReturnBy());
		
		bookService.updateBorrow(newBook);
		
		
		return "redirect:/mybooks";
	}
	

//	DELETE REQUESTS
	
	@DeleteMapping("/book/{bookKey}/delete")
	public String deleteBorrow(
			@PathVariable String bookKey
			) {
		
		if( checkLogin() ) { return "redirect:/login"; }
		
		Book bookToDelete = bookService.getBookByKey(bookKey);
		if( bookToDelete.getBorrower().getId() != (long) session.getAttribute("loggedInUser")  ) {
			return "redirect:/dashboard";
		}
		
		bookService.deleteBorrowById(bookToDelete.getId());
		System.out.println("DELETE FUNCTION SUCESSFUL");
		return "redirect:/mybooks";
	}
	
	
	private Boolean checkLogin() {
		Long userId = (Long) session.getAttribute("loggedInUser");
		
		return userId == null || userService.findById( userId ) == null ? true : false;
	}
	
}
