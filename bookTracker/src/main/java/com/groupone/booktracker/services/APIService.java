package com.groupone.booktracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.groupone.booktracker.dtos.SearchBookDocDTO;
import com.groupone.booktracker.dtos.SearchBookResponseDTO;

@Service
public class APIService {
	@Autowired
	private RestTemplate restTemplate;
	
	public String convertQuery(String initial) {
		return initial.trim().replaceAll("\\s+", "+");
	}
	
	public List<SearchBookDocDTO> findByQuery(String query) {
		String uri = "https://openlibrary.org/search.json?q=" + convertQuery(query);
		return findByUri(uri);
	}
	
	public List<SearchBookDocDTO> findByUri(String uri) {
		ResponseEntity<SearchBookResponseDTO> response = restTemplate.getForEntity(uri, SearchBookResponseDTO.class);
		SearchBookResponseDTO searchResponse = response.getBody();
		
		return searchResponse.getDocs();
	}
}
