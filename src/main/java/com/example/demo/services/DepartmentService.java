package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.InputDTOs.DepartmentInputDTO;
import com.example.demo.dtos.OutputDTOs.DepartmentOutputDTO;
import com.example.demo.mappers.DepartmentMapper;
import com.example.demo.models.Department;
import com.example.demo.repositories.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Optional<DepartmentOutputDTO> findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentMapper::toDto);
    }

    public List<DepartmentOutputDTO> findAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepartmentOutputDTO createDepartment(DepartmentInputDTO departmentInputDTO) {
        Department department = departmentMapper.toEntity(departmentInputDTO);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toDto(savedDepartment);
    }

    public DepartmentOutputDTO updateDepartment(Long departmentId, DepartmentInputDTO departmentInputDTO) {
        return departmentRepository.findById(departmentId)
                .map(department -> {
                    department.setDepartmentName(departmentInputDTO.getDepartmentName());
                    department.setDescription(departmentInputDTO.getDescription());
                    return departmentMapper.toDto(departmentRepository.save(department));
                }).orElseThrow(() -> new RuntimeException("Department not found with ID: " + departmentId));
    }

    public void deleteDepartmentById(Long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            departmentRepository.deleteById(departmentId);
        } else {
            throw new RuntimeException("Department not found with ID: " + departmentId);
        }
    }

    public DepartmentOutputDTO patchDepartment(Long id, DepartmentInputDTO inputDTO) {
        Department existingDepartment = departmentRepository.findById(id)
                .map(department -> {
                    if (inputDTO.getDepartmentName() != null) {
                        department.setDepartmentName(inputDTO.getDepartmentName());
                    }
                    if (inputDTO.getDescription() != null) {
                        department.setDescription(inputDTO.getDescription());
                    }
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
        return departmentMapper.toDto(existingDepartment);
    }
}