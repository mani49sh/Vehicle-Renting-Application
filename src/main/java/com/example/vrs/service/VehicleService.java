package com.example.vrs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.vrs.entity.Vehicle;
import com.example.vrs.exceptions.VehicleNotFoundException;
import com.example.vrs.repository.VehicleRepository;

@Service
public class VehicleService {
	private final VehicleRepository vehicleRepository;

	public VehicleService(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	public Vehicle updateVehicle(Vehicle vehicle) {
		Optional<Vehicle> optional=vehicleRepository.findById(vehicle.getVehicleId());
		if(optional.isPresent()) {
			return vehicleRepository.save(vehicle);
		}
		else {
			throw new VehicleNotFoundException("Failed to find the vehicle");
		}
	}

	public List<Vehicle> findAllVehicles() {
		List<Vehicle> vehicles=vehicleRepository.findAll();
		if(vehicles.size() !=0) {
			return vehicles;
		}
		else {
			throw new VehicleNotFoundException("Failed to find the Vehicle");
		}
	}
}
