package com.example.vrs.security;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.vrs.entity.User;
import com.example.vrs.repository.UserRepository;


@Component
public class AuthUtil {

	private final UserRepository userRepository;

	@Autowired
	public AuthUtil(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Authentication getCurrentAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getCurrentUsername() {
		return getCurrentAuthentication().getName();
	}

	public User getCurrentUser() {
		return userRepository.findByUsername(this.getCurrentUsername())
				.orElseThrow(() -> new UsernameNotFoundException("Failed to authenticate username."));
	}

}
