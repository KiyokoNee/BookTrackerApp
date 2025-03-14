package com.groupone.booktracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupone.booktracker.dtos.BookDetailsDTO;
import com.groupone.booktracker.mappers.BookMapper;
import com.groupone.booktracker.models.Book;
import com.groupone.booktracker.models.User;
import com.groupone.booktracker.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private APIService apiServ;
	
	public List<Book> getBooksByBorrowerId(Long borrowerId) {
		return bookRepo.findByBorrowerId(borrowerId);
	}
	
	public Book getBookById(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		return optionalBook.isPresent() ? optionalBook.get() : null;
	}
	
	public Book getBookByKey(String key) {
		Optional<Book> optionalBook = bookRepo.findByBookKey(key);
		return optionalBook.isPresent() ? optionalBook.get() : null;
	}
	
	public Book borrowBookByKey(String bookKey, User borrower) {
		Book newBook = BookMapper.bookDTOToBook(apiServ.findByKey(bookKey), borrower);
		return bookRepo.save(newBook);
	}
	
	public Book borrowBook(Book book) {
		return bookRepo.save(book);
	}
	
	public Book updateBorrow(Book book) {
		return bookRepo.save(book);
	}
	
	public void deleteBorrowById(Long id) {
		bookRepo.deleteById(id);
	}
}
