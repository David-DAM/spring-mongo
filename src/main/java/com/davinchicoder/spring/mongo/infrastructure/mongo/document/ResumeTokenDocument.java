package com.davinchicoder.spring.mongo.infrastructure.mongo.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.BsonDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("resume_tokens")
@EqualsAndHashCode(callSuper = true)
@Data
public class ResumeTokenDocument extends BaseDocument {

    @Id
    private String id;

    private BsonDocument token;
}
