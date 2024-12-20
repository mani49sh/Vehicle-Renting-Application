package com.example.vrs.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.vrs.service.VehicleListingService;

@RestController
public class VehicleListingController {
	private final VehicleListingService vehicleListingService;

	public VehicleListingController(VehicleListingService vehicleListingService) {
		super();
		this.vehicleListingService = vehicleListingService;
	}
	

}
