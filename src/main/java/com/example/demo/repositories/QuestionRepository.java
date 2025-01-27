package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Custom methods can be added if necessary
}
