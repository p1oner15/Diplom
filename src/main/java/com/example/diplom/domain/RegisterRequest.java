package com.example.diplom.domain;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String passport;
    private String citizenship;
    private Date dateOfBirth;
    private String phoneNumber;
}