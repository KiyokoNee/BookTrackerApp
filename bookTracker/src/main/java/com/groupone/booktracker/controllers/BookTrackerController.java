package com.groupone.booktracker.controllers;

import java.util.ArrayList;
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
import com.groupone.booktracker.models.Author;
import com.groupone.booktracker.models.Book;
import com.groupone.booktracker.models.LoginUser;
import com.groupone.booktracker.models.Subject;
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
	
	// Will remove later, using for testing purposes
	@GetMapping("/test")
	public String test() {
		return "index.jsp";
	}

	// TESTING COMMENTS
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
			@PathVariable("bookKey") String bookKey,
			Model view
			) {
		
		
		BookDetailsDTO result = apiServ.findByKey(bookKey);
		List<String> authors = apiServ.getAuthorNames(result);
		String img = apiServ.getImageURLByKey(bookKey);
		view.addAttribute("book", result);
		view.addAttribute("imgURL", img);
		view.addAttribute("authors", authors);
		
		return "bookDetails.jsp";
	}
	
	@PostMapping("/borrow/{bookKey}")
	public String borrow(
			@PathVariable("bookKey") String bookKey
			) {
		
		if( checkLogin() ) { return "redirect:/login"; }
		
		User borrower = userService.findById( (Long) session.getAttribute("loggedInUser") );
		Book borrowingBook = new Book();
		BookDetailsDTO result = apiServ.findByKey(bookKey);
		List<Author> bookAuthors = new ArrayList<Author>();
		List<String> authors = apiServ.getAuthorNames(result);
		List<Subject> bookSubjects = new ArrayList<Subject>();
		List<String> subjects = result.getSubjects();
		
		borrowingBook.setTitle(result.getTitle());
		borrowingBook.setSubtitle(result.getSubtitle());
		borrowingBook.setDescription(result.getDescription().getValue());
		borrowingBook.setTotalPages(result.getNumber_of_pages());
		borrowingBook.setBookKey(bookKey);
		borrowingBook.setAuthors(null);
		borrowingBook.setSubjects(null);
		borrowingBook.setBorrower( borrower );
		borrowingBook = bookService.borrowBook(borrowingBook);
		
		for(String strAuthor:authors) {
			Author athr = new Author();
			athr.setName(strAuthor);
			athr.setBook(borrowingBook);
			bookAuthors.add(athr);
		}
		borrowingBook.setAuthors(bookAuthors);
		
		for(String strSubject:subjects) {
			Subject sbj = new Subject();
			sbj.setSubject(strSubject);
			sbj.setBook(borrowingBook);
			bookSubjects.add(sbj);
		}
		borrowingBook.setSubjects(bookSubjects);
		
		bookService.updateBorrow(borrowingBook);
		
		return "redirect:/mybooks";
	}
	
	@GetMapping("/register")
	public String registerForm(
			@ModelAttribute("newUser") User newUser
			) {
		if( !checkLogin() ) { return "redirect:/dashboard"; }
		
		return "registration.jsp";
	}
	
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
	
	@GetMapping("/login")
	public String loginForm(
			@ModelAttribute("loginUser") LoginUser loginUser
			) {
		if( !checkLogin() ) { return "redirect:/dashboard"; }
		
		return "login.jsp";
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
	
	@PostMapping("/logout")
	public String logout() {
		
		session.setAttribute("loggedInUser", null);
		return "redirect:/"; 
	}	
	
	@GetMapping("/dashboard")
	public String dashboard() {
		
		if( checkLogin() ) { return "redirect:/login"; }
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/mybooks")
	public String myBooks() {
		return "index.jsp";
	}
	
	
	@GetMapping("/book/{key}/edit")
	public String editBookForm() {
		return "index.jsp";
	}
	
	// Post Requests - In Progress
	
	
	
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
	
	private Boolean checkLogin() {
		Long userId = (Long) session.getAttribute("loggedInUser");
		
		return userId == null || userService.findById( userId ) == null ? true : false;
	}
	
}
