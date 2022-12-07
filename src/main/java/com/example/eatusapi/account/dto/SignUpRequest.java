package com.example.eatusapi.account.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    String userId;
    String password;
    String userName;
}
