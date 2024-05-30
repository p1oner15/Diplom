package com.example.diplom.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passport;
    private String citizenship;
    private Date dateOfBirth;
    private String phoneNumber;

}
