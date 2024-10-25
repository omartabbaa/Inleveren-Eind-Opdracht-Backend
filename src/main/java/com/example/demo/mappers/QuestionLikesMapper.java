package com.example.demo.mappers;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.InputDTOs.QuestionLikesInputDTO;
import com.example.demo.dtos.OutputDTOs.QuestionLikesOutputDTO;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionLikes;
import com.example.demo.models.User;

@Component
public class QuestionLikesMapper {

    // Convert QuestionLikes Entity to QuestionLikesOutputDTO
    public QuestionLikesOutputDTO toDto(QuestionLikes questionLike) {
        QuestionLikesOutputDTO dto = new QuestionLikesOutputDTO();
        dto.setLikeId(questionLike.getLikeId());
        dto.setQuestionId(questionLike.getQuestion().getQuestionId());
        dto.setUserId(questionLike.getUser().getUserId());
        dto.setCreatedAt(questionLike.getCreatedAt());
        return dto;
    }

    // Convert QuestionLikesInputDTO to QuestionLikes Entity
    public QuestionLikes toEntity(QuestionLikesInputDTO questionLikesInputDTO, User user, Question question) {
        QuestionLikes questionLike = new QuestionLikes();
        questionLike.setUser(user);  // User entity already retrieved
        questionLike.setQuestion(question);  // Question entity already retrieved
        questionLike.setLikeId(questionLikesInputDTO.getLikeId());
        return questionLike;
    }
}
