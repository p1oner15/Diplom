package com.example.diplom.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthDto {
    private String email;
    private String password;
}