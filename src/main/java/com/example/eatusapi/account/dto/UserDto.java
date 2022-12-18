package com.example.eatusapi.account.dto;


import lombok.Data;

import java.util.Date;
@Data
public class UserDto {
    String userId;
    String userName;
    String address;
    String addressDetail;
    Date createdAt;
}
