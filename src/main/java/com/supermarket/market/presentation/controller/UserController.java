package com.supermarket.market.presentation.controller;

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
    public ResponseEntity<?> getEveryUsers() {
        return userService.getEveryUsers();
    }

    @PutMapping("/addProducts")
    public void addProducts(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        userService.addProductToUser(userId, productId, quantity);
    }

    @PutMapping("/removeProducts")
    public void removeProducts(@RequestParam Long userId, @RequestParam Long productId){
        userService.removeProductFromUser(userId, productId);
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.getEveryUsers();
    }
}
