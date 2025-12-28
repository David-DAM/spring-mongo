package com.davinchicoder.spring.mongo.infrastructure.scheduler;

import com.davinchicoder.spring.mongo.domain.EventType;
import com.davinchicoder.spring.mongo.domain.FinancialEvent;
import com.davinchicoder.spring.mongo.infrastructure.mongo.repository.financial_event.FinancialEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinancialEventLoaderScheduler {

    private final FinancialEventRepository repository;

    @Scheduled(cron = "0/30 * * * * *")
    public void listen() {
        log.info("Loading financial events...");
        List<FinancialEvent> events = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            FinancialEvent event = new FinancialEvent();
            event.setCurrencyId(i % 2 == 0 ? "EUR" : "USD");
            event.setType(i % 2 == 0 ? EventType.BUY : EventType.SELL);
            event.setPrice(BigDecimal.valueOf(random.nextDouble()));
            event.setSource(List.of(random.nextDouble(), random.nextDouble()));
            event.setTimestamp(Instant.now());

            events.add(event);
        }

        repository.upsertAll(events);
        log.info("Financial events loaded.");
    }
}
