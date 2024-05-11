package com.learning.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learning.entity.User;
import com.learning.service.UserService;



@Controller()
@RequestMapping(value = "/home/admin/")
public class AdminController {

	@Autowired
	private UserService userServiceImpl;
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		// principal will give current logged in user name
		String userName = principal.getName();
		System.out.println(userName);

		// get user from db
		Optional<User> findBy = this.userServiceImpl.findUserByEmail(userName);
		User user = findBy.get();
		System.out.println(user);
		model.addAttribute("user", user);
	}
	
	@GetMapping("/index")
	public String adminHome(Model model) {
		model.addAttribute("title", "CCPL-Admin Dashboard");
		return "admin/admin";
	}

	
	
}