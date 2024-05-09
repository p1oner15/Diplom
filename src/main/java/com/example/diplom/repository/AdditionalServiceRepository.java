package com.example.diplom.repository;

import com.example.diplom.domain.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {
}

