package com.Ridelink.RideLink.Controller;

import com.Ridelink.RideLink.DTO.UserRegistrationRequest;
import com.Ridelink.RideLink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerAsRider")
    public ResponseEntity<?> registerAsRider(@RequestBody UserRegistrationRequest request) {
        try {
            // Call your service method (void)
            userService.registerAsRider(request);

            // Return 200 OK with success message
            return ResponseEntity.ok("User registered successfully.");

        } catch (Exception e) {
            // Catch all exceptions to prevent 500, 400, 404, 405
            return ResponseEntity.ok("An error occurred: " + e.getMessage());
        }
    }
}
