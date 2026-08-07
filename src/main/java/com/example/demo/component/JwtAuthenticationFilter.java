package com.example.demo.component;


import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	int count=0;

    @Autowired
    private JWUtil jwUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
    	
    	
        count++;
        System.out.println("JWT Filter EXECUTED"+count);
        
        String authHeader = request.getHeader("token");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	System.out.println("authHeader not null");
            String token = authHeader.substring(7);
            try {
                String username = jwUtil.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                  
                    UsernamePasswordAuthenticationToken auth = 
                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    //principal->username, credentials are not needed here, role -user or admin
                    //List.of(
                    //    new SimpleGrantedAuthority("ROLE_USER"),
                    //    new SimpleGrantedAuthority("ROLE_ADMIN")
                    //)
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());
                }
            } catch (Exception e) {
               
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
        
  
    }
}
