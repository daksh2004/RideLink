package com.Ridelink.RideLink.Service;

import com.Ridelink.RideLink.DTO.UserRegistrationRequest;
import com.Ridelink.RideLink.Entity.Rider;
import com.Ridelink.RideLink.Entity.User;
import com.Ridelink.RideLink.Repository.RiderRepository;
import com.Ridelink.RideLink.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiderRepository riderRepository;

    // Register User (Passenger or Rider)
    public String registerUser(UserRegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }

        try {
            User user = new User();
            user.setUserName(request.getUserName());
            user.setMobileNo(request.getMobileNo());
            user.setEmail(request.getEmail());

            // Default role = PASSENGER
            String role = (request.getRole() == null || request.getRole().isBlank())
                    ? "PASSENGER"
                    : request.getRole().toUpperCase();

            user.setRole(role);

            User savedUser = userRepository.save(user);

            // If role is RIDER, create Rider entry
            if ("RIDER".equalsIgnoreCase(role)) {
                Rider rider = new Rider();
                rider.setLicenseNumber(request.getLicenseNumber());
                rider.setRcNumber(request.getRcNumber());
                rider.setAadharNumber(request.getAadharNumber());
                rider.setUser(savedUser);

                riderRepository.save(rider);
                return "Rider registered successfully";
            }

            return "Passenger registered successfully";

        } catch (Exception e) {
            return "An error occurred during registration";
        }
    }

    // Register Rider for existing User
    public String registerAsRider(UserRegistrationRequest request) {

        try {
            if (riderRepository.existsByAadharNumber(request.getAadharNumber())) {
                return "Rider already registered with this Aadhar number";
            }

            User user = userRepository.findByUserId(request.getUserId());
            if (user == null) {
                return "User not found with given userId";
            }

            Rider rider = new Rider();
            rider.setLicenseNumber(request.getLicenseNumber());
            rider.setRcNumber(request.getRcNumber());
            rider.setAadharNumber(request.getAadharNumber());
            rider.setUser(user);

            riderRepository.save(rider);
            return "Rider registration successful";

        } catch (Exception e) {
            return "Please enter correct details";
        }
    }
}
