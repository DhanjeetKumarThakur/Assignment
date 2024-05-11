package com.learning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.dao.UserRepository;
import com.learning.entity.User;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userDao;
	
	@Override
	public User saveUser(User user) {
		user.setRole("ROLE_NORMAL_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
		return userDao.save(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		 return userDao.findById(email);
	}

}
