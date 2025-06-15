package com.supermarket.market.presentation.controller;

import com.supermarket.market.data.model.UserEntity;
import com.supermarket.market.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createUser")
    public void createUser() {
        UserEntity user = new UserEntity();
        user.setUsername("Eduardo");
        user.setCreditos(2.5);
        userRepository.save(user);
    }
}
