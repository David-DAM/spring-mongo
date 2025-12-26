package com.davinchicoder.spring.mongo.infrastructure.repository;

import com.davinchicoder.spring.mongo.infrastructure.document.FinancialEventDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FinancialEventRepository {

    private final FinancialEventQueryRepository repository;

    public void upsert(FinancialEventDocument event) {
        repository.save(event);
    }

    public void upsertAll(List<FinancialEventDocument> events) {
        repository.saveAll(events);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<FinancialEventDocument> findAll() {
        return repository.findAll();
    }

}
