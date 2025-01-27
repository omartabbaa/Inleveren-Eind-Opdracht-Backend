package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.InputDTOs.BusinessInputDTO;
import com.example.demo.dtos.OutputDTOs.BusinessOutputDTO;
import com.example.demo.services.BusinessService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    // GET /api/businesses/{businessId}
    @GetMapping("/{businessId}")
    public ResponseEntity<BusinessOutputDTO> getBusinessById(@PathVariable Long businessId) {
        Optional<BusinessOutputDTO> business = businessService.findBusinessById(businessId);
        return business.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // GET /api/businesses
    @GetMapping
    public ResponseEntity<List<BusinessOutputDTO>> getAllBusinesses() {
        List<BusinessOutputDTO> businesses = businessService.findAllBusinesses();
        return ResponseEntity.ok(businesses);
    }

    // POST /api/businesses
    @PostMapping
    public ResponseEntity<BusinessOutputDTO> createBusiness(@RequestBody BusinessInputDTO businessInputDTO) {
        BusinessOutputDTO createdBusiness = businessService.createBusiness(businessInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBusiness);
    }

    // PUT /api/businesses/{businessId}
    @PutMapping("/{businessId}")
    public ResponseEntity<BusinessOutputDTO> updateBusiness(@PathVariable Long businessId,
                                                            @RequestBody BusinessInputDTO businessInputDTO) {
        try {
            BusinessOutputDTO updatedBusiness = businessService.updateBusiness(businessId, businessInputDTO);
            return ResponseEntity.ok(updatedBusiness);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE /api/businesses/{businessId}
    @DeleteMapping("/{businessId}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long businessId) {
        try {
            businessService.deleteBusinessById(businessId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
