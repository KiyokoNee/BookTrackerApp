package com.groupone.booktracker.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.groupone.booktracker.models.LoginUser;
import com.groupone.booktracker.models.User;
import com.groupone.booktracker.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		// Create errors if the following conditions are flagged
		if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
			result.rejectValue("email", "error.email", "This email is already in use!");
		}
		if(userRepo.findByUsername(newUser.getUsername()).isPresent()) {
			result.rejectValue("username", "error.username", "This username is already in use!");
		}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Matches", "The Confirm Password must match Password!");
		}
		
		// Exit the method due to invalid submission data
		if(result.hasErrors()) {
			return null;
		}
		
		// Create hashed Password to store then set the password to the hashed variant
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		// Pull out potential user data to verify
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		
		// Create errors if the following conditions are flagged
		if(potentialUser.isEmpty()) {
			result.rejectValue("email", "error.email", "Invalid Credentials!");
		} else if (!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())) {
			result.rejectValue("email", "error.email", "Invalid Credentials!");
		}
		
		// Exit method due to invalid submission data
		if(result.hasErrors()) {
			return null;
		}
		
		return potentialUser.get();
	}
	
	public User findById(long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}
	
	
}
