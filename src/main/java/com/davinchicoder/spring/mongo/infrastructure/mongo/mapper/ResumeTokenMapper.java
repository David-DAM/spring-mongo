package com.davinchicoder.spring.mongo.infrastructure.mongo.mapper;

import com.davinchicoder.spring.mongo.domain.ResumeToken;
import com.davinchicoder.spring.mongo.infrastructure.mongo.document.ResumeTokenDocument;
import org.bson.BsonDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ResumeTokenMapper {

    ResumeTokenDocument toDocument(ResumeToken token);

    ResumeToken toDomain(ResumeTokenDocument document);

    default BsonDocument toBsonDocument(String token) {
        return BsonDocument.parse(token);
    }

    default String toString(BsonDocument token) {
        return token.toJson();
    }
}
