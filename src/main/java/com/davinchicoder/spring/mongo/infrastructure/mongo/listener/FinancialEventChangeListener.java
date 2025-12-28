package com.davinchicoder.spring.mongo.infrastructure.mongo.listener;

import com.davinchicoder.spring.mongo.domain.FinancialEvent;
import com.davinchicoder.spring.mongo.domain.ResumeToken;
import com.davinchicoder.spring.mongo.infrastructure.mongo.document.FinancialEventDocument;
import com.davinchicoder.spring.mongo.infrastructure.mongo.mapper.FinancialEventMapper;
import com.davinchicoder.spring.mongo.infrastructure.mongo.repository.resume_token.ResumeTokenRepository;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonDocument;
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
    private final ResumeTokenRepository tokenRepository;

    private static final String TOKEN_ID = "financial-events";

    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        log.info("Listening for financial events...");
        BsonDocument resumeToken = loadResumeToken();

        try {
            ChangeStreamIterable<Document> changeStream;

            if (resumeToken == null) {
                changeStream = mongoTemplate.getCollection("events").watch();
            } else {
                changeStream = mongoTemplate.getCollection("events")
                        .watch()
                        .resumeAfter(resumeToken);
            }

            try (MongoCursor<ChangeStreamDocument<Document>> cursor = changeStream.iterator()) {

                cursor.forEachRemaining(changeEvent -> {
                    log.info("Received event: {}", changeEvent);

                    Document fullDoc = changeEvent.getFullDocument();

                    if (fullDoc == null) return;

                    FinancialEventDocument eventDocument =
                            mongoTemplate.getConverter().read(FinancialEventDocument.class, fullDoc);

                    FinancialEvent domain = mapper.toDomain(eventDocument);

                    log.info("Received event: {}", domain);


                    BsonDocument newResumeToken = changeEvent.getResumeToken();
                    saveResumeToken(newResumeToken);
                });
            }

        } catch (Exception e) {
            log.error("Change stream failed", e);
            listen();
        }

    }

    private BsonDocument loadResumeToken() {
        return tokenRepository.findById(TOKEN_ID)
                .map(ResumeToken::getToken)
                .map(BsonDocument::parse)
                .orElse(null);
    }

    private void saveResumeToken(BsonDocument token) {
        if (token == null) return;

        ResumeToken resumeToken = new ResumeToken();
        resumeToken.setId(TOKEN_ID);
        resumeToken.setToken(token.toJson());

        tokenRepository.upsert(resumeToken);
    }
}
