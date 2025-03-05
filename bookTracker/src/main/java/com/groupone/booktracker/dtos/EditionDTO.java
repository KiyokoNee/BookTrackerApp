package com.groupone.booktracker.dtos;

import java.util.List;

public class EditionDTO {
	private Long numFound;
	private Long start;
	private boolean numFoundExact;
	private List<EditionDetailsDTO> docs;
	
	public EditionDTO() {
		
	}

	public Long getNumFound() {
		return numFound;
	}

	public void setNumFound(Long numFound) {
		this.numFound = numFound;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public boolean isNumFoundExact() {
		return numFoundExact;
	}

	public void setNumFoundExact(boolean numFoundExact) {
		this.numFoundExact = numFoundExact;
	}

	public List<EditionDetailsDTO> getDocs() {
		return docs;
	}

	public void setDocs(List<EditionDetailsDTO> docs) {
		this.docs = docs;
	}

}
