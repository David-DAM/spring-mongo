package com.davinchicoder.spring.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResumeToken extends BaseDomain {

    private String id;
    private String token;
}
