package com.example.vrs.mapper;

import org.springframework.stereotype.Component;

import com.example.vrs.entity.User;
import com.example.vrs.requestdto.UserRequest;
import com.example.vrs.responsedto.UserResponse;

@Component
public class UserMapper {

	public User mapToUser(UserRequest request, User user) {

		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setPassword(request.getPassword());

		return user;
	}

	public UserResponse mapToUserResponse(User user) {

		UserResponse response = new UserResponse();

		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setPhoneNumber(user.getPhoneNumber());
		response.setRole(user.getRole());
		
		return response;
	}

}
