package com.davinchicoder.spring.mongo.infrastructure.listener;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinancialEventChangeListener {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void listen() {

        mongoTemplate.getCollection("events")
                .watch()
                .forEach(event -> {
                    var fullDoc = event.getFullDocument();

                });
    }
}
