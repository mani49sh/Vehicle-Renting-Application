package com.example.vrs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(); 
	 }
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()) 
		 .authorizeHttpRequests(authorize -> authorize.requestMatchers("/customer/register","/renting-partner/register","/account","/admin/register")
//				 .permitAll().requestMatchers("/add-vehicle").hasAuthority("ADMIN")// 
				 //we used @PreAuthorize in Vehicle controller class on essential methods
				 .permitAll()
				 .anyRequest()
				 .authenticated())
		 .formLogin(Customizer.withDefaults())
		 .build();
	}
}


/**
 * CSRF--> Cross Site Request Forgery (WE HAVE NOT USED CSRF IN THIS PROJECT; IF WE WANT WE CAN USE IT)
 */

