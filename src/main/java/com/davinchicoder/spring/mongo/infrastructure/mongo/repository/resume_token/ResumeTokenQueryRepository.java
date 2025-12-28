package com.davinchicoder.spring.mongo.infrastructure.mongo.repository.resume_token;

import com.davinchicoder.spring.mongo.infrastructure.mongo.document.ResumeTokenDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeTokenQueryRepository extends MongoRepository<ResumeTokenDocument, String> {
}
