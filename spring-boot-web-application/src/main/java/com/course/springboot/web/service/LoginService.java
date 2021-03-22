package com.course.springboot.web.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	
	public boolean validate(String username, String password) {
		return username.equalsIgnoreCase("akhil") && password.equalsIgnoreCase("akki");
	}
}
