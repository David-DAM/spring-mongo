package com.davinchicoder.spring.mongo.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Data
public class FinancialEvent {
    
    private String id;

    private String currencyId;
    private Instant timestamp;
    private BigDecimal price;
    private EventType type;

    private List<Double> source;

}
