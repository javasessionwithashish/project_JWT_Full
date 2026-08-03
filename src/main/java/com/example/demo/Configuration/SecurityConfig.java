package com.example.demo.Configuration;

import com.example.demo.component.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)  {

    http.csrf(csrf -> csrf.disable());

    http.authorizeHttpRequests(auth -> auth
    		.requestMatchers("/api/loginjwt","/api/login").permitAll()
    		.requestMatchers("/api/**").authenticated()
    	
    		.anyRequest().permitAll()

    );

    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    
    
    return http.build();
}

}
