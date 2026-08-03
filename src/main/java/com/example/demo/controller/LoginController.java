package com.example.demo.controller;

import com.example.demo.component.JWUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@Autowired
	private JWUtil jwutil;

	
	@GetMapping("/")
	public String login()
	{
		return  "index.html";
	}


	@PostMapping("/login")
	public String loginPost(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");




		return "home";
	}
	
}
