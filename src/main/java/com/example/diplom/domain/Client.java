package com.example.diplom.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "Clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String country;
    private String passportDetails;
}

