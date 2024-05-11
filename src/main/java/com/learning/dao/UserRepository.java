package com.learning.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	public Optional<User> findByEmail(String Email);
}
