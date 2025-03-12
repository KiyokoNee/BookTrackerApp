package com.groupone.booktracker.dtos;

public class EditionDetailsDTO {
	private String key;
	private String title;
	private String subtitle;
	private String description;
	
	public EditionDetailsDTO() {
		
	}

	public EditionDetailsDTO(String key, String title, String subtitle, String description) {
		this.key = key;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
