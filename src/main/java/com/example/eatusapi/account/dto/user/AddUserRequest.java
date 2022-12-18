package com.example.eatusapi.account.dto.user;

import lombok.Data;

@Data
public class AddUserRequest {
    String userId;
    String userName;
    String password;
    String address;
    String addressDetail;

}
