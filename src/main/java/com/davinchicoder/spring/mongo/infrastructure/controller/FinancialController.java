package com.davinchicoder.spring.mongo.infrastructure.controller;

import com.davinchicoder.spring.mongo.infrastructure.mapper.FinancialEventMapper;
import com.davinchicoder.spring.mongo.infrastructure.repository.FinancialEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/financial")
@RestController
public class FinancialController {

    private final FinancialEventRepository repository;
    private final FinancialEventMapper mapper;

    @GetMapping
    public ResponseEntity<List<FinancialEventDto>> getAllFinancialEvents() {
        List<FinancialEventDto> dtos = repository.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialEventDto> getFinancialEventById(@PathVariable String id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

