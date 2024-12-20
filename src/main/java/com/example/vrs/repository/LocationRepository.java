package com.example.vrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vrs.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
