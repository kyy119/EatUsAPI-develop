package com.example.eatusapi.account.service;

import com.example.eatusapi.account.dto.TokenDto;
import com.example.eatusapi.account.entity.User;
import com.example.eatusapi.account.repository.UserRepository;
import com.example.eatusapi.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class AuthService {
    public UserRepository userRepository;
    public TokenProvider tokenProvider;
    public PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    AuthService(UserRepository userRepository, TokenProvider tokenProvider, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(String uid,String uname,String upass, String uadd,String uaddD,String role)
    {
        User user;

        if(role.equalsIgnoreCase("admin"))
        {
            user = User.builder()
                    .userId(uid)
                    .userName(uname)
                    .password(passwordEncoder.encode(upass))
                    .address(uadd)
                    .addressDetail(uaddD)
                    .createdAt(new Date())
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }
        else
        {
            user = User.builder()
                    .userId(uid)
                    .userName(uname)
                    .password(passwordEncoder.encode(upass))
                    .address(uadd)
                    .addressDetail(uaddD)
                    .createdAt(new Date())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);

        if(savedUser.getUsername().isEmpty())
        {
            logger.info("회원가입 실패");
        }
        else {
            logger.info("회원가입 완료");
        }
    }

    public TokenDto signIn(String uid, String password)
    {
        User user = userRepository.getByUserId(uid);
        logger.info("id : {}",uid);

        if(!passwordEncoder.matches(password,user.getPassword()))
        {
            throw new RuntimeException();
        }

        TokenDto tokenDto = TokenDto.builder()
                .token(tokenProvider.createToken(user.getUserId(),user.getRoles()))
                .build();

        tokenDto.setCode(1);
        tokenDto.setSuccess(true);
        tokenDto.setMessage("success");


        return tokenDto;
    }
}

