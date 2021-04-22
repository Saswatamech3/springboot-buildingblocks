package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent() ) {
			throw new UserNotFoundException("User Not Found in User Repositor");
		}
		return user;
		
	}
	
	public User updateUserById(Long id,User user) throws UserNotFoundException{
		Optional<User> optionalUser = userRepository.findById(id);
		user.setId(id);
		
		if (!optionalUser.isPresent() ) {
			throw new UserNotFoundException("User Not Found in User Repositor");
		}
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) throws UserNotFoundException {
		Optional<User> deleteUser = userRepository.findById(id);
		
		if (!deleteUser.isPresent()) {
			throw new UserNotFoundException("User Not Found in User Repositor");
		}
			
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);		
		
	}
}
	
	
