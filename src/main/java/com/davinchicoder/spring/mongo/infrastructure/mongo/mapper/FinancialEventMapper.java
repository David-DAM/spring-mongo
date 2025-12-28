package com.davinchicoder.spring.mongo.infrastructure.mongo.mapper;

import com.davinchicoder.spring.mongo.domain.FinancialEvent;
import com.davinchicoder.spring.mongo.infrastructure.mongo.document.FinancialEventDocument;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FinancialEventMapper {

    FinancialEventDocument toDocument(FinancialEvent event);

    FinancialEvent toDomain(FinancialEventDocument document);

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default String toString(ObjectId id) {
        return id == null ? null : id.toHexString();
    }

    default GeoJsonPoint toGeoJsonPoint(List<Double> coordinates) {
        return coordinates == null ? null : new GeoJsonPoint(coordinates.getFirst(), coordinates.getLast());
    }

    default List<Double> toCoordinates(GeoJsonPoint point) {
        return point == null ? null : List.of(point.getX(), point.getY());
    }

}
