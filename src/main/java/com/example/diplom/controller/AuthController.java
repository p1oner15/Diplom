package com.example.diplom.controller;

import com.example.diplom.domain.Client;
import com.example.diplom.domain.JwtResponse;
import com.example.diplom.domain.LoginRequest;
import com.example.diplom.domain.RegisterRequest;
import com.example.diplom.service.UserService;
import com.example.diplom.service.jwt.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).build();
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        Client user = new Client();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassport(signUpRequest.getPassport()); // Устанавливаем паспорт
        user.setCitizenship(signUpRequest.getCitizenship()); // Устанавливаем гражданство
        user.setDateOfBirth(signUpRequest.getDateOfBirth()); // Устанавливаем дату рождения
        user.setPhoneNumber(signUpRequest.getPhoneNumber());


        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}