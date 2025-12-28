package com.davinchicoder.spring.mongo.infrastructure.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record FinancialEventDto(
        String id,
        String currencyId,
        Instant timestamp,
        BigDecimal price,
        List<Double> source
) {
}
