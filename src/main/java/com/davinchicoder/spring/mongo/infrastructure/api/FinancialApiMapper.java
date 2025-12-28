package com.davinchicoder.spring.mongo.infrastructure.api;

import com.davinchicoder.spring.mongo.domain.FinancialEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FinancialApiMapper {

    FinancialEventDto toDto(FinancialEvent event);

}
