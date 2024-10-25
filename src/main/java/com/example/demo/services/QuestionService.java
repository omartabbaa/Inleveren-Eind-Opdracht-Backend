package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Question;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.dtos.OutputDTOs.QuestionOutputDTO;
import com.example.demo.dtos.InputDTOs.QuestionInputDTO;
import com.example.demo.mappers.QuestionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Optional<QuestionOutputDTO> findQuestionById(Long questionId) {
        return questionRepository.findById(questionId).map(questionMapper::toDto);
    }

    public List<QuestionOutputDTO> findAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }

    public QuestionOutputDTO createQuestion(QuestionInputDTO questionInputDTO) {
        Question question = questionMapper.toEntity(questionInputDTO, null); // Assuming project is not set here
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toDto(savedQuestion);
    }

    public QuestionOutputDTO updateQuestion(Long questionId, QuestionInputDTO questionInputDTO) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setQuestionTitle(questionInputDTO.getQuestionTitle());
                    question.setQuestionText(questionInputDTO.getQuestionText());
                    return questionMapper.toDto(questionRepository.save(question));
                }).orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));
    }

    public void deleteQuestionById(Long questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
        } else {
            throw new RuntimeException("Question not found with ID: " + questionId);
        }
    }
}
