package com.example.diplom.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "clients")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passport;
    private String citizenship;
    private Date dateOfBirth;
    private String phoneNumber;
    // Другие поля класса
}
