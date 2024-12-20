package com.example.vrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.vrs.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
