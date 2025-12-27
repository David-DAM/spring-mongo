package com.davinchicoder.spring.mongo.infrastructure.repository;

import com.davinchicoder.spring.mongo.domain.FinancialEvent;
import com.davinchicoder.spring.mongo.infrastructure.document.FinancialEventDocument;
import com.davinchicoder.spring.mongo.infrastructure.mapper.FinancialEventMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FinancialEventRepository {

    private final FinancialEventQueryRepository repository;
    private final FinancialEventMapper mapper;

    public void upsert(FinancialEvent event) {
        FinancialEventDocument document = mapper.toDocument(event);
        repository.save(document);
    }

    public void upsertAll(List<FinancialEvent> events) {
        List<FinancialEventDocument> documents = events.stream().map(mapper::toDocument).toList();
        repository.saveAll(documents);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<FinancialEvent> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    public Optional<FinancialEvent> findById(String id) {
        return repository.findById(new ObjectId(id)).map(mapper::toDomain);
    }

}
