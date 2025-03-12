package com.groupone.booktracker.dtos;

public class BookDetailsAuthorsDTO {
	private String key;
	
	public BookDetailsAuthorsDTO() {
		
	}

	public BookDetailsAuthorsDTO(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
