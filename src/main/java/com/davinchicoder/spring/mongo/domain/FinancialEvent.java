package com.davinchicoder.spring.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class FinancialEvent extends BaseDomain {

    private String id;
    private String currencyId;
    private Instant timestamp;
    private EventType type;
    private BigDecimal price;
    private List<Double> source;

}
