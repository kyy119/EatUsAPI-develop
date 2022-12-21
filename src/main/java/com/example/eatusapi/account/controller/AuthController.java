package com.example.eatusapi.account.controller;

import com.example.eatusapi.account.dto.SignInRequest;
import com.example.eatusapi.account.dto.SignUpRequest;
import com.example.eatusapi.account.dto.TokenDto;
import com.example.eatusapi.account.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @ResponseBody
    @PostMapping("/signIn")
    public TokenDto signIn(@RequestBody SignInRequest request) throws RuntimeException
    {
        TokenDto tokenDto = authService.signIn(request.getId(),request.getPassword());

        if(tokenDto.getCode()==1){
            logger.info("로그인 완료");
        }

        return tokenDto;
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody SignUpRequest request)
    {
        authService.signUp(request.getUserId(), request.getUserName(),request.getPassword(),request.getAddress(),
                request.getAddressDetail(),request.getRole());
    }


}
