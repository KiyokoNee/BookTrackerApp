package com.groupone.booktracker.dtos;

import java.util.List;

public class BookDetailsDTO {
	private String title;
	private List<Long> covers;
	private String key;
	private List<BookDetailsAuthorsDTO> authors;
	private TypeDTO type;
	private List<String> subjectPeople;
	private DescriptionDTO description;
	private List<String> subjectPlaces;
	private List<String> subjects;
	private List<String> subjectTimes;
	private Long latestRevision;
	private Long revision;
	private DateDTO created;
	private DateDTO lastModified;
	
	public BookDetailsDTO() {
		
	}

	public BookDetailsDTO(String title, List<Long> covers, String key, List<BookDetailsAuthorsDTO> authors,
			TypeDTO type, List<String> subjectPeople, DescriptionDTO description, List<String> subjectPlaces,
			List<String> subjects, List<String> subjectTimes, Long latestRevision, Long revision, DateDTO created,
			DateDTO lastModified) {
		super();
		this.title = title;
		this.covers = covers;
		this.key = key;
		this.authors = authors;
		this.type = type;
		this.subjectPeople = subjectPeople;
		this.description = description;
		this.subjectPlaces = subjectPlaces;
		this.subjects = subjects;
		this.subjectTimes = subjectTimes;
		this.latestRevision = latestRevision;
		this.revision = revision;
		this.created = created;
		this.lastModified = lastModified;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Long> getCovers() {
		return covers;
	}

	public void setCovers(List<Long> covers) {
		this.covers = covers;
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

	public TypeDTO getType() {
		return type;
	}

	public void setType(TypeDTO type) {
		this.type = type;
	}

	public List<String> getSubjectPeople() {
		return subjectPeople;
	}

	public void setSubjectPeople(List<String> subjectPeople) {
		this.subjectPeople = subjectPeople;
	}

	public DescriptionDTO getDescription() {
		return description;
	}

	public void setDescription(DescriptionDTO description) {
		this.description = description;
	}

	public List<String> getSubjectPlaces() {
		return subjectPlaces;
	}

	public void setSubjectPlaces(List<String> subjectPlaces) {
		this.subjectPlaces = subjectPlaces;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public List<String> getSubjectTimes() {
		return subjectTimes;
	}

	public void setSubjectTimes(List<String> subjectTimes) {
		this.subjectTimes = subjectTimes;
	}

	public Long getLatestRevision() {
		return latestRevision;
	}

	public void setLatestRevision(Long latestRevision) {
		this.latestRevision = latestRevision;
	}

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	public DateDTO getCreated() {
		return created;
	}

	public void setCreated(DateDTO created) {
		this.created = created;
	}

	public DateDTO getLastModified() {
		return lastModified;
	}

	public void setLastModified(DateDTO lastModified) {
		this.lastModified = lastModified;
	}
}
