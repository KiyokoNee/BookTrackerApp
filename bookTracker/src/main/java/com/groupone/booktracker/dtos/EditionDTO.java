package com.groupone.booktracker.dtos;

import java.util.List;

public class EditionDTO {
	private List<EditionDetailsDTO> docs;
	
	public EditionDTO() {
		
	}

	public EditionDTO(List<EditionDetailsDTO> docs) {
		this.docs = docs;
	}

	public List<EditionDetailsDTO> getDocs() {
		return docs;
	}

	public void setDocs(List<EditionDetailsDTO> docs) {
		this.docs = docs;
	}

}
