package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Custom methods can be added if necessary
}
