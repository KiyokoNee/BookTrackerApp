package com.groupone.booktracker.mappers;

import java.util.ArrayList;
import java.util.List;

import com.groupone.booktracker.dtos.BookDetailsDTO;
import com.groupone.booktracker.models.Author;
import com.groupone.booktracker.models.Book;
import com.groupone.booktracker.models.Subject;
import com.groupone.booktracker.models.User;
import com.groupone.booktracker.services.APIService;

public class BookMapper {
	private static final APIService apiServ = new APIService();
	
	public static Book bookDTOToBook(BookDetailsDTO bookData, User borrower) {
		Book newBook = new Book();
		
		newBook.setTitle(bookData.getTitle());
		newBook.setSubtitle(bookData.getSubtitle());
		newBook.setDescription(bookData.getDescription() != null ? bookData.getDescription().getValue() : null);
		newBook.setTotalPages(bookData.getNumber_of_pages());
		newBook.setBookKey(bookData.getKey());
		newBook.setBorrower(borrower);
		newBook.setAuthors(convertStringsToAuthors(apiServ.getAuthorNames(bookData), newBook));
		newBook.setSubjects(convertStringsToSubjects(bookData.getSubjects(), newBook));
		
		return newBook;
	}
	
	private static List<Author> convertStringsToAuthors(List<String> names, Book book) {
		List<Author> authors = new ArrayList<>();
		if(names == null)
			return authors;
		
		for(String name : names) {
			authors.add(new Author(name, book));
		}
		
		return authors;
	}
	
	private static List<Subject> convertStringsToSubjects(List<String> names, Book book) {
		List<Subject> subjects = new ArrayList<>();
		if(names == null)
			return subjects;
		
		for(String name : names) {
			subjects.add(new Subject(name, book));
		}
		
		return subjects;
	}
}
