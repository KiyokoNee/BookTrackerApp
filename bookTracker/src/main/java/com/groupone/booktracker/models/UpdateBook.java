package com.groupone.booktracker.models;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;

public class UpdateBook {
	@Min(value = 0, message ="Pages read cannot be less than 0!")
	private Long pagesRead;
	private LocalDate returnBy;
	
	public UpdateBook() {
	}

	public UpdateBook(Long pagesRead, LocalDate returnBy) {
		this.pagesRead = pagesRead;
		this.returnBy = returnBy;
	}

	public Long getPagesRead() {
		return pagesRead;
	}

	public void setPagesRead(Long pagesRead) {
		this.pagesRead = pagesRead;
	}

	public LocalDate getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(LocalDate returnBy) {
		this.returnBy = returnBy;
	}
}
