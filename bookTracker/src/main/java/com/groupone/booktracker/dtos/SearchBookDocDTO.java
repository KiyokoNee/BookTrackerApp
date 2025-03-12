package com.groupone.booktracker.dtos;

import java.util.List;

public class SearchBookDocDTO {
	private List<String> authorName;
	private Long firstPublishYear;
	private String key;
	private String title;
	private EditionDTO editions;
	
	public SearchBookDocDTO() {
		
	}

	public SearchBookDocDTO(List<String> authorName, Long firstPublishYear, String key, String title,
			EditionDTO editions) {
		this.authorName = authorName;
		this.firstPublishYear = firstPublishYear;
		this.key = key;
		this.title = title;
		this.editions = editions;
	}

	public List<String> getAuthorName() {
		return authorName;
	}

	public void setAuthorName(List<String> authorName) {
		this.authorName = authorName;
	}

	public Long getFirstPublishYear() {
		return firstPublishYear;
	}

	public void setFirstPublishYear(Long firstPublishYear) {
		this.firstPublishYear = firstPublishYear;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EditionDTO getEditions() {
		return editions;
	}

	public void setEditions(EditionDTO editions) {
		this.editions = editions;
	}
}
