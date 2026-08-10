package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.component.JWUtil;

@RestController
public class APIController {

	@Autowired
	private JWUtil util;
	
	
	@GetMapping("/api/hey")
	public String hey()
	{
		return "HEY";
	}
	
	
	@GetMapping("/api/new")
	public String newGet()
	{
		return "new";
	}
	
	
	 @PostMapping("/api/login")
	    public String login(@RequestParam String username,
	                        @RequestParam String password) {

	        if ("admin".equals(username) && "1234".equals(password)) {
	            return "TOKEN_ABC_123";
	        }

	        return "INVALID";
	    }
	
	 @GetMapping("/api/secure")
	 public String secure(@RequestHeader("token") String token) {

	     if ("TOKEN_ABC_123".equals(token)) {
	         return "Access granted";
	     }

	     return "Access denied";
	 }
	 
	 
	 @PostMapping("/api/loginjwt")
	    public String loginjwt(@RequestParam String username,
	                        @RequestParam String password) {

	        if ("admin".equals(username) && "1234".equals(password)) {
	            return util.generateToken(username);
	        }

	        return "INVALID";
	    }
	
	 @GetMapping("/api/securejwt")
	 public String securejwt(@RequestHeader("token") String token) {

	     String tok = token.substring(7);
	     
	     String user = util.extractUsername(tok);

	     return "Hello :" +user;
	 }
	 
	 
}
