package com.learning.service;

import java.util.Optional;

import com.learning.entity.User;


public interface UserService {
	public User saveUser(User user) ;

	public Optional<User> findUserByEmail(String email);

}
