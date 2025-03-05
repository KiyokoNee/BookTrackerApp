package com.groupone.booktracker.dtos;

import java.util.List;

public class BookDetailsDTO {
	private String title;
	private String subtitle;
	private String key;
	private List<BookDetailsAuthorsDTO> authors;
	private DescriptionDTO description;
	private List<String> subjects;
	private Long number_of_pages;
	
	public BookDetailsDTO() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<BookDetailsAuthorsDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<BookDetailsAuthorsDTO> authors) {
		this.authors = authors;
	}

	public DescriptionDTO getDescription() {
		return description;
	}

	public void setDescription(DescriptionDTO description) {
		this.description = description;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Long getNumber_of_pages() {
		return number_of_pages;
	}

	public void setNumber_of_pages(Long number_of_pages) {
		this.number_of_pages = number_of_pages;
	}
}
