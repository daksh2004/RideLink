package com.Ridelink.RideLink.Controller;

import com.Ridelink.RideLink.Entity.User;
import com.Ridelink.RideLink.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin") // ✅ leading slash is important
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        Object response; // Can hold List<User> or error message

        try {
            // Fetch all users
            List<User> allUsers = adminService.getAllUsers();

            // If no users exist, fallback message
            if (allUsers == null || allUsers.isEmpty()) {
                response = "No users found.";
            } else {
                response = allUsers;
            }
        } catch (Exception e) {
            // Catch any unexpected error
            response = "An error occurred while fetching users: " + e.getMessage();
        }

        // Always return 200 OK
        return ResponseEntity.ok(response);
    }
}
