package com.example.demo.mappers;

import org.springframework.stereotype.Component;
import com.example.demo.models.Answer;
import com.example.demo.models.User;
import com.example.demo.models.Question;
import com.example.demo.dtos.OutputDTOs.AnswerOutputDTO;
import com.example.demo.dtos.InputDTOs.AnswerInputDTO;

@Component
public class AnswerMapper {

    // Convert Answer Entity to AnswerOutputDTO
    public AnswerOutputDTO toDto(Answer answer) {
        AnswerOutputDTO dto = new AnswerOutputDTO();
        dto.setAnswerId(answer.getAnswerId());
        dto.setAnswerText(answer.getAnswerText());
        dto.setQuestionId(answer.getQuestion().getQuestionId());
        dto.setUserId(answer.getUser().getUserId());
        dto.setCreatedAt(answer.getCreatedAt());
        return dto;
    }

    // Convert AnswerInputDTO to Answer Entity
    public Answer toEntity(AnswerInputDTO answerInputDTO, User user, Question question) {
        Answer answer = new Answer();
        answer.setAnswerText(answerInputDTO.getAnswerText());
        answer.setUser(user);  // User entity already retrieved
        answer.setQuestion(question);  // Question entity already retrieved
        return answer;
    }
}
