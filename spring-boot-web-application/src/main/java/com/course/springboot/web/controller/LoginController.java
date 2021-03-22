package com.course.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.course.springboot.web.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	//LoginService service = new LoginService();
		@Autowired
		LoginService service;
	
		@RequestMapping(value="/login", method=RequestMethod.GET)
		//@ResponseBody 
		public String loginMessage( ModelMap model) {
			return "login";
		}
		@RequestMapping(value="/login", method=RequestMethod.POST)
		//@ResponseBody 
		public String showWelcome( ModelMap model, @RequestParam String name , @RequestParam String password) {

			model.put("name", name);
			model.put("password", password);
			if(service.validate(name, password)) {
			return "welcome";}
			else { 
				String message="Invalid credentials ";
				model.put("message", message);
				return "login";}
		}
		
		
	}


