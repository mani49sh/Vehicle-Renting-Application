package com.example.vrs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.vrs.enums.UserRole;
import com.example.vrs.requestdto.UserRequest;
import com.example.vrs.responsedto.UserResponse;
import com.example.vrs.service.UserService;
import com.example.vrs.util.ResponseStructure;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/customer/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerCustomer(@RequestBody UserRequest request) {

		UserResponse response = userService.register(request, UserRole.CUSTOMER);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Customer Created", response));

	}

	@PostMapping("/renting-partner/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerRentingPartner(@RequestBody UserRequest request) {

		UserResponse response = userService.register(request, UserRole.RENTING_PARTNER);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Renting Partner Created", response));

	}

	@GetMapping("/account")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser() {

		UserResponse response = userService.findUser();

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(ResponseStructure.create(HttpStatus.FOUND.value(), "User Found", response));
	}

	@PutMapping("/update-user")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest request) {
		
		UserResponse response = userService.updateUser(request);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "User Updated", response));
	}
	
	@PostMapping("/admin/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(@RequestBody UserRequest request) {

		UserResponse response = userService.register(request, UserRole.ADMIN);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Admin created", response));

	}

}
