package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionLikes;
import com.example.demo.models.User;

public interface QuestionLikesRepository extends JpaRepository<QuestionLikes, Long> {
    QuestionLikes findByQuestionAndUser(Question question, User user);
}
