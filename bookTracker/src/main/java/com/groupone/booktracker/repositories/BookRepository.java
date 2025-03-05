package com.groupone.booktracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.booktracker.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
