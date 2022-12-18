package com.example.eatusapi.account.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    String userId;
    String password;
    String userName;
    String address;
    String addressDetail;
    String role;
}
