package com.groupone.booktracker.dtos;

public class FindAuthorDTO {
	private String name;
	
	public FindAuthorDTO() {
		
	}

	public FindAuthorDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
