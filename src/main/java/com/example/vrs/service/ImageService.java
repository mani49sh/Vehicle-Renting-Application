package com.example.vrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.vrs.entity.Image;
import com.example.vrs.entity.User;
import com.example.vrs.entity.Vehicle;
import com.example.vrs.exceptions.FailedToUploadException;
import com.example.vrs.exceptions.ProfilePictureNotFoundException;
import com.example.vrs.exceptions.UserNotFoundException;
import com.example.vrs.repository.ImageRepository;
import com.example.vrs.repository.UserRepository;
import com.example.vrs.repository.VehicleRepository;

@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final UserRepository userRepository;
	private final VehicleRepository vehicleRepository;

	public ImageService(ImageRepository imageRepository, UserRepository userRepository,
			VehicleRepository vehicleRepository) {
		super();
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
		this.vehicleRepository = vehicleRepository;
	}

	public void uploadUserProfilePicture(int userId, MultipartFile file) {

		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			Image exImage = optional.get().getProfilePicture();
			Image image = imageRepository.save(this.getImage(file));
			User user = optional.get();
			user.setProfilePicture(image);
			userRepository.save(user);

			if (exImage != null) {
				imageRepository.delete(exImage);
			}

		} else {

			throw new UserNotFoundException("User not Found");
		}
	}

	public void uploadVehicleImages(int vehicleId, List<MultipartFile> files) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found with ID: " + vehicleId));

		List<Image> images = new ArrayList<>();

		for (MultipartFile file : files) {
			images.add(this.getImage(file));
		}

		images = imageRepository.saveAll(images);
		vehicle.setImages(images);
		vehicleRepository.save(vehicle);
	}
	

	private Image getImage(MultipartFile file) {

		Image image = new Image();
		try {
			byte[] imageBytes = file.getBytes();
			image.setContentType(file.getContentType());
			image.setImageBytes(imageBytes);
			return image;

		} catch (Exception e) {

			throw new FailedToUploadException("Failed to upload image");
		}
	}

	public Image findImageById(int imageId) {

		Optional<Image> optional = imageRepository.findById(imageId);
		if (optional.isPresent()) {

			Image image = optional.get();

			return image;
		} else {

			throw new ProfilePictureNotFoundException("Image not Found");
		}

	}

}
