package com.davinchicoder.spring.mongo.infrastructure.mongo.repository.financial_event;

import com.davinchicoder.spring.mongo.infrastructure.mongo.document.FinancialEventDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialEventQueryRepository extends MongoRepository<FinancialEventDocument, ObjectId> {
}
