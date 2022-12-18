package com.example.eatusapi.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class TokenDto {
    private boolean success;
    private int code;
    private String message;
    private String token;

    @Builder
    public TokenDto(boolean success, int code, String message, String token)
    {
        this.code = code;
        this.success = success;
        this.message = message;
        this.token = token;
    }
}
