package com.example.vrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vrs.entity.User;

public interface VehicleListingRepository extends JpaRepository<User, Integer> {
	

}
