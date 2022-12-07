package com.example.eatusapi.account.dto;

public class TokenDto {
    String grantType;
    String accessToken;
    String refreshToken;
    private Long accessTokenExpiresIn;
}
