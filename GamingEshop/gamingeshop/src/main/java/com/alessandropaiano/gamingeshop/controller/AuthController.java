package com.alessandropaiano.gamingeshop.controller;

import com.alessandropaiano.gamingeshop.dto.UserDTO;
import com.alessandropaiano.gamingeshop.model.User;
import com.alessandropaiano.gamingeshop.service.JwtService;
import com.alessandropaiano.gamingeshop.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;



    public AuthController(UserService userService, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.register(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User existingUser = userService.getUserByUsername(userDTO.getUsername());

        if (existingUser != null && passwordEncoder.matches(userDTO.getPassword(), existingUser.getPassword())) {
            String token = jwtService.generateToken(userDTO.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    }


