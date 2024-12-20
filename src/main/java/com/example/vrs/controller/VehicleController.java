package com.example.vrs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.vrs.entity.Vehicle;
import com.example.vrs.service.VehicleService;
import com.example.vrs.util.ResponseStructure;

@RestController
public class VehicleController {
	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/add-vehicle")
	public ResponseEntity<ResponseStructure<Vehicle>> addVehicle (@RequestBody Vehicle vehicle) {
	     Vehicle vehicle1 = vehicleService.addVehicle(vehicle);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Vehicle Details added", vehicle1));

	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update-vehicle")
	public ResponseEntity<ResponseStructure<Vehicle>> updateVehicle (@RequestBody Vehicle vehicle) {
	     vehicle = vehicleService.updateVehicle(vehicle);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "Vehicle Details updated", vehicle)); 

	}
	

	@GetMapping("find-all-vehicles")
	public ResponseEntity<ResponseStructure<List<Vehicle>>> findAllVehicles () {
	    List<Vehicle> vehicles= vehicleService.findAllVehicles();

		return ResponseEntity.status(HttpStatus.FOUND).
				body(ResponseStructure.create(HttpStatus.OK.value(), "Vehicle Details updated", vehicles)); 

	}
	
}
