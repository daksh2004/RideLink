package com.Ridelink.RideLink.Controller;

import com.Ridelink.RideLink.DTO.UserRegistrationRequest;
import com.Ridelink.RideLink.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rideLink/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        String message;
        try {
            message = userService.registerUser(request);

            if (message == null || message.isBlank()) {
                message = "User registration completed successfully.";
            }
        } catch (Exception e) {
            message = "An error occurred, but request completed: " + e.getMessage();
        }
        return ResponseEntity.ok(message);
    }
}
