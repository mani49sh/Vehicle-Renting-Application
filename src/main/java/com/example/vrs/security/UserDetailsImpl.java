package com.example.vrs.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.vrs.entity.User;
import com.example.vrs.enums.UserRole;


public class UserDetailsImpl implements UserDetails {
	private final User user;
	
	public UserDetailsImpl(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		UserRole[] roles = UserRole.values();
//		List<GrantedAuthority> authorities=new ArrayList<>();
//		for(UserRole role: roles) {
//			authorities.add(new SimpleGrantedAuthority(role.name()));
//		}
//		return authorities;
		
		return List.of(new SimpleGrantedAuthority(user.getRole().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getEmail();
	}
}


