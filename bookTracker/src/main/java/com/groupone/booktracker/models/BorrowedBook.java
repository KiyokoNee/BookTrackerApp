package com.groupone.booktracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BorrowedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
