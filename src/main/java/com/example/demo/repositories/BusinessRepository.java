package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    // Custom methods can be added if necessary
}
