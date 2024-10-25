package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);  // Custom method to find a user by email
}
