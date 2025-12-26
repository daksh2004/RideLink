package com.Ridelink.RideLink.DTO;

import lombok.Data;

@Data
public class UserRegistrationRequest {

    private Long userId;          // Optional, can be null during registration
    private String userName;      // Required
    private String mobileNo;      // Required (NOT NULL in DB)
    private String email;         // Required (NOT NULL in DB)
    private String role;

    private String licenseNumber; // Optional
    private String rcNumber;      // Optional
    private String aadharNumber;  // Optional
}
