package com.example.demo.component;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JWUtil {

	private final String secret_key="This is my supersecret key that is supersecret";

	public String generateToken (String username)
	{


		String token=  Jwts.builder() //initalises a builder for constructing jwt
				.setSubject(username)
				.signWith(Keys.hmacShaKeyFor(secret_key.getBytes()))
				.compact(); //jwt final form -> header, payload and signature
	return token;
	}

	//creates username by parsing jwt token and extract username from claims
	public String extractUsername(String token)
	{
		return Jwts.parserBuilder()
				.setSigningKey(secret_key.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}








}
