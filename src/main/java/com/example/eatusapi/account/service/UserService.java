package com.example.eatusapi.account.service;

import com.example.eatusapi.account.dto.UserDto;
import com.example.eatusapi.account.entity.User;
import com.example.eatusapi.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    UserDto getUser(Long id)
    {
        User user = userRepository.getReferenceById(id);
        UserDto userDto = change(user);

        return userDto;
    }

    UserDto change(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setAddress(user.getAddress());
        userDto.setAddressDetail(user.getAddressDetail());
        userDto.setCreatedAt(user.getCreatedAt());
        return userDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUserId(username);
    }
}
