package com.example.vrs.service;

import org.springframework.stereotype.Service;

import com.example.vrs.repository.VehicleListingRepository;

@Service
public class VehicleListingService {
	private final VehicleListingRepository vehicleListingRepository;

	public VehicleListingService(VehicleListingRepository vehicleListingRepository) {
		super();
		this.vehicleListingRepository = vehicleListingRepository;
	}

	
}
