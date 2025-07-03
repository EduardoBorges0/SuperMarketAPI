package com.supermarket.market.presentation.controller;

import com.supermarket.market.config.JwtUtil;
import com.supermarket.market.config.SecurityConfig;
import com.supermarket.market.data.model.entiity.AuthRequest;
import com.supermarket.market.domain.dto.UserDTO;
import com.supermarket.market.domain.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    private SecurityConfig securityConfig;
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthUserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping()
    public ResponseEntity<?> auth(@RequestBody AuthRequest auth){
        List<UserDTO> allUsers = userService.getAllUsersDTO();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean validUser = allUsers.stream()
                .anyMatch(user -> user.getEmail().equals(auth.getEmail())
                        && passwordEncoder.matches(auth.getPassword(), user.getPassword())
                );
        Long id = allUsers.stream().filter(user -> {
           user.getId();
           return user.getEmail().equals(auth.getEmail())
                   && passwordEncoder.matches(auth.getPassword(), user.getPassword());
        }).map(
                user -> user.getId()
        ).findFirst().orElse(null);

        if (validUser) {
            String token = jwtUtil.generateToken(id.toString());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        return userService.createUser(userDTO.getUsername(), userDTO.getEmail(), encodedPassword);
    }

}
