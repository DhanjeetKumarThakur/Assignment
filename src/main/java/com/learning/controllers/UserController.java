package com.learning.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learning.service.UserService;

@Controller
@RequestMapping(value = "/home/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "User Dashboard");
		return "admin/admin";
	}

}