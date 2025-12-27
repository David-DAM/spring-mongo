package com.davinchicoder.spring.mongo.infrastructure.controller;

import com.davinchicoder.spring.mongo.domain.EventType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record FinancialEventDto(
        String id,
        String currencyId,
        Instant timestamp,
        BigDecimal price,
        EventType type,
        List<Double> source
) {
}
