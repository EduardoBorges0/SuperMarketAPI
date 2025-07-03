package com.supermarket.market.presentation.controller;

import com.supermarket.market.config.JwtUtil;
import com.supermarket.market.domain.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getEveryUsers")
    public ResponseEntity<?> getEveryUsers(){
        return userService.getEveryUsers();
    }

}
