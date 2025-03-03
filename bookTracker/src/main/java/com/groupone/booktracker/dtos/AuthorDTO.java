package com.groupone.booktracker.dtos;

public class AuthorDTO {
	private String key;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
