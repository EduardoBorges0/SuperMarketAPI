package com.supermarket.market.presentation.controller;

import com.supermarket.market.config.JwtUtil;
import com.supermarket.market.domain.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping
    public String addProductInCart(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtUtil.validateTokenAndGetEmail(token);
    }

}
