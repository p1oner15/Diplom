package com.example.diplom.service;

import com.example.diplom.domain.Client;
import com.example.diplom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private ClientRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client saveUser(Client user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByEmail(username);
    }
}