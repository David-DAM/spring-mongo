package com.davinchicoder.spring.mongo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseDomain {

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String lastModifiedBy;
}
