package com.davinchicoder.spring.mongo.infrastructure.listener;

import com.davinchicoder.spring.mongo.domain.FinancialEvent;
import com.davinchicoder.spring.mongo.infrastructure.document.FinancialEventDocument;
import com.davinchicoder.spring.mongo.infrastructure.mapper.FinancialEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinancialEventChangeListener {

    private final MongoTemplate mongoTemplate;
    private final FinancialEventMapper mapper;

    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        log.info("Listening for financial events...");
        mongoTemplate.getCollection("events")
                .watch()
                .forEach(event -> {
                    Document fullDoc = event.getFullDocument();
                    FinancialEventDocument eventDocument = mongoTemplate.getConverter().read(FinancialEventDocument.class, fullDoc);
                    FinancialEvent domain = mapper.toDomain(eventDocument);
                    log.info("Received event: {}", domain);
                });
    }
}
