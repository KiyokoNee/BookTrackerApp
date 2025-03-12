package com.groupone.booktracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.booktracker.dtos.BookDetailsAuthorsDTO;
import com.groupone.booktracker.dtos.BookDetailsDTO;
import com.groupone.booktracker.dtos.FindAuthorDTO;
import com.groupone.booktracker.dtos.SearchBookDocDTO;
import com.groupone.booktracker.dtos.SearchBookResponseDTO;

@Service
public class APIService {
	@Autowired
	private RestTemplate restTemplate;
	private String http = "https://openlibrary.org";
	
	public APIService() {
		this.restTemplate = new RestTemplate();
	}
	
	// Turns the user input into a query that can be added to the URI
	public String convertQuery(String initial) {
		return initial.trim().replaceAll("\\s+", "+");
	}
	
	// Cleans the key so it can repurposed to make different calls
	public String cleanKey(String key) {
		return key.trim().replaceAll("/books/", "");
	}
	
	// Call api and return the details based on the key. The key is cleaned to be sure we send the proper URI call.
	public BookDetailsDTO findByKey(String key) {
		String cleanKey = cleanKey(key);
		String uri = http + "/books/" + cleanKey + ".json";
		ResponseEntity<BookDetailsDTO> response = restTemplate.getForEntity(uri, BookDetailsDTO.class);
		
		return response.getBody();
	}
	
	// Call api and return the string for the cover URI. This can be put directly in an img tag to display the cover art.
	public String getImageURLByKey(String key) {
		String cleanKey = cleanKey(key);
		return "https://covers.openlibrary.org/b/olid/" + cleanKey + ".jpg";
	}
	
	public List<String> getAuthorNames(BookDetailsDTO book) {
		List<String> authors = new ArrayList<>();
		if(book.getAuthors() != null){
			for(BookDetailsAuthorsDTO author: book.getAuthors()) {
				ResponseEntity<FindAuthorDTO> response = restTemplate.getForEntity("https://openlibrary.org/" + author.getKey() + ".json", FindAuthorDTO.class);
				FindAuthorDTO currAuthor = response.getBody();
				authors.add(currAuthor.getName());
			}
		}
		
		return authors;
	}
	
	// Searches for books by restructuring the query and sending it as a URI to the findByUri method
	public List<SearchBookDocDTO> findByQuery(String query) {
		String uri = http + "/search.json?q=" + convertQuery(query) + "&fields=key,title,subtitle,author_name,editions,first_publish_year,description";
		return findByUri(uri);
	}
	
	// Calls the API using the specified URI parses the response and sends the documents received
	public List<SearchBookDocDTO> findByUri(String uri) {
		ResponseEntity<SearchBookResponseDTO> response = restTemplate.getForEntity(uri, SearchBookResponseDTO.class);
		SearchBookResponseDTO searchResponse = response.getBody();
		
		return searchResponse.getDocs();
	}
}
