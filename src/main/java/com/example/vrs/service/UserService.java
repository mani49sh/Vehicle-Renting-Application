package com.example.vrs.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vrs.entity.Image;
import com.example.vrs.entity.User;
import com.example.vrs.enums.UserRole;
import com.example.vrs.mapper.UserMapper;
import com.example.vrs.repository.ImageRepository;
import com.example.vrs.repository.UserRepository;
import com.example.vrs.requestdto.UserRequest;
import com.example.vrs.responsedto.UserResponse;
import com.example.vrs.security.AuthUtil;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthUtil authUtil;


	public UserService(UserRepository userRepository, 
			PasswordEncoder passwordEncoder,
			ImageRepository imageRepository, 
			UserMapper userMapper,
			AuthUtil authUtil) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.authUtil = authUtil;


	}

	public UserResponse register(UserRequest request,UserRole role) {

		User user = userMapper.mapToUser(request, new User());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(role);
		User savedUser = userRepository.save(user);

		return userMapper.mapToUserResponse(savedUser);
	}

	public UserResponse findUser() {


		User user = authUtil.getCurrentUser();

		UserResponse response = userMapper.mapToUserResponse(user);
		this.setProfilePictureURL(response, user.getProfilePicture());

		return response;
	}

	private void setProfilePictureURL(UserResponse response, Image profilePicture) {

		if (profilePicture!= null)
			response.setProfilePictureLink("/find-image-by-id?image-id=" + profilePicture.getImageId());

	}

	public UserResponse updateUser(UserRequest request) {
        // Get current user using AuthUtil

        User updatedUser = userMapper.mapToUser(request, authUtil.getCurrentUser());
        userRepository.save(updatedUser);

        UserResponse response = userMapper.mapToUserResponse(updatedUser);
        this.setProfilePictureURL(response, updatedUser.getProfilePicture());

        return response;
    }

}



