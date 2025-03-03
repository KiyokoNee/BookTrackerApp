package com.groupone.booktracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupone.booktracker.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
