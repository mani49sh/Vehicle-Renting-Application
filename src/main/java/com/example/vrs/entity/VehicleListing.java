package com.example.vrs.entity;

import com.example.vrs.enums.seating;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VehicleListing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listingId;
	private String vehicleNo;
	private double pricePerDay;
	
	@Enumerated(EnumType.STRING)//stores the enum in the form as a string in the database
	private seating seat;

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public seating getSeat() {
		return seat;
	}

	public void setSeat(seating seat) {
		this.seat = seat;
	}
	

}
