package com.example.diplom.repository;

import com.example.diplom.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    boolean existsByEmail(String email);

    Client findByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);
}