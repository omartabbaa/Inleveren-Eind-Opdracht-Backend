package com.example.demo.mappers;

import org.springframework.stereotype.Component;
import com.example.demo.models.User;
import com.example.demo.dtos.OutputDTOs.UserOutputDTO;
import com.example.demo.dtos.InputDTOs.UserInputDTO;

@Component
public class UserMapper {

    // Convert User Entity to UserOutputDTO
    public UserOutputDTO toDto(User user) {
        UserOutputDTO dto = new UserOutputDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    // Convert UserInputDTO to User Entity
    public User toEntity(UserInputDTO userInputDTO) {
        User user = new User();
        user.setName(userInputDTO.getName());
        user.setEmail(userInputDTO.getEmail());
        user.setRole(userInputDTO.getRole());
        user.setPassword(userInputDTO.getPassword());  // Ensure password is hashed before saving
        return user;
    }
}
