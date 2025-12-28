package com.davinchicoder.spring.mongo.infrastructure.mongo.repository.resume_token;

import com.davinchicoder.spring.mongo.domain.ResumeToken;
import com.davinchicoder.spring.mongo.infrastructure.mongo.document.ResumeTokenDocument;
import com.davinchicoder.spring.mongo.infrastructure.mongo.mapper.ResumeTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ResumeTokenRepository {

    private final ResumeTokenQueryRepository repository;
    private final ResumeTokenMapper mapper;

    public void upsert(ResumeToken token) {
        ResumeTokenDocument document = mapper.toDocument(token);
        repository.save(document);
    }

    public Optional<ResumeToken> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
