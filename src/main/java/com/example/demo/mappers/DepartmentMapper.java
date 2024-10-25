package com.example.demo.mappers;

import org.springframework.stereotype.Component;
import com.example.demo.models.Department;
import com.example.demo.dtos.OutputDTOs.DepartmentOutputDTO;
import com.example.demo.dtos.InputDTOs.DepartmentInputDTO;

@Component
public class DepartmentMapper {

    // Convert Department Entity to DepartmentOutputDTO
    public DepartmentOutputDTO toDto(Department department) {
        DepartmentOutputDTO dto = new DepartmentOutputDTO();
        dto.setDepartmentId(department.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDescription(department.getDescription());
        return dto;
    }

    // Convert DepartmentInputDTO to Department Entity
    public Department toEntity(DepartmentInputDTO departmentInputDTO) {
        Department department = new Department();
        department.setDepartmentName(departmentInputDTO.getDepartmentName());
        department.setDescription(departmentInputDTO.getDescription());
        return department;
    }
}
