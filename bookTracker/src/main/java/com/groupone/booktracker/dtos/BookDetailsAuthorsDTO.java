package com.groupone.booktracker.dtos;

public class BookDetailsAuthorsDTO {
	private AuthorDTO author;
	private TypeDTO type;
	
	public BookDetailsAuthorsDTO() {
		
	}
	
	public BookDetailsAuthorsDTO(AuthorDTO author, TypeDTO type) {
		this.author = author;
		this.type = type;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public TypeDTO getType() {
		return type;
	}

	public void setType(TypeDTO type) {
		this.type = type;
	}
}
