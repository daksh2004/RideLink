package com.Ridelink.RideLink.Controller;

import com.Ridelink.RideLink.DTO.UserRegistrationRequest;
import com.Ridelink.RideLink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rideLink/public") // base public APIs
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {

        String message;

        try {
            // Service always returns a message
            message = userService.registerUser(request);

            if (message == null || message.isBlank()) {
                message = "User registration completed successfully";
            }

        } catch (Exception e) {
            // Never throw error to client
            message = "An error occurred, but request completed";
        }

        // Always return 200 OK
        return ResponseEntity.ok(message);
    }
}
