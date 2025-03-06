package com.groupone.booktracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.booktracker.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByBookKey(String bookKey);
	
	List<Book> findByBorrowerId(Long id);
}
