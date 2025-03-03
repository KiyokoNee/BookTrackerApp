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
	
	public String convertQuery(String initial) {
		return initial.trim().replaceAll("\\s+", "+");
	}
	
	public String cleanKey(String key) {
		return key.replaceAll("/works/", "");
	}
	
	public BookDetailsDTO findByKey(String key) {
		String cleanKey = cleanKey(key);
		String uri = http + "/works/" + cleanKey + ".json";
		ResponseEntity<BookDetailsDTO> response = restTemplate.getForEntity(uri, BookDetailsDTO.class);
		
		return response.getBody();
	}
	
	public List<SearchBookDocDTO> findByQuery(String query) {
		String uri = http + "/search.json?q=" + convertQuery(query);
		return findByUri(uri);
	}
	
	public List<SearchBookDocDTO> findByUri(String uri) {
		ResponseEntity<SearchBookResponseDTO> response = restTemplate.getForEntity(uri, SearchBookResponseDTO.class);
		SearchBookResponseDTO searchResponse = response.getBody();
		
		return searchResponse.getDocs();
	}
}
