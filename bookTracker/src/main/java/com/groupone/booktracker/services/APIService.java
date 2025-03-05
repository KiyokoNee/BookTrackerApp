package com.groupone.booktracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.booktracker.dtos.BookDetailsDTO;
import com.groupone.booktracker.dtos.SearchBookDocDTO;
import com.groupone.booktracker.dtos.SearchBookResponseDTO;

@Service
public class APIService {
	@Autowired
	private RestTemplate restTemplate;
	private String http = "https://openlibrary.org";
	
	// Turns the user input into a query that can be added to the URI
	public String convertQuery(String initial) {
		return initial.trim().replaceAll("\\s+", "+");
	}
	
	// Cleans the key so it can repurposed to make different calls
	public String cleanKey(String key) {
		return key.trim().replaceAll("/works/", "");
	}
	
	// Call api and return the details based on the key. The key is cleaned to be sure we send the proper URI call.
	public BookDetailsDTO findByKey(String key) {
		String cleanKey = cleanKey(key);
		String uri = http + "/works/" + cleanKey + ".json";
		ResponseEntity<BookDetailsDTO> response = restTemplate.getForEntity(uri, BookDetailsDTO.class);
		
		return response.getBody();
	}
	
	// Call api and return the string for the cover URI. This can be put directly in an img tag to display the cover art.
	public String getImageURLByKey(String key) {
		String cleanKey = cleanKey(key);
		return "https://covers.openlibrary.org/w/olid/" + cleanKey + ".jpg";
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
