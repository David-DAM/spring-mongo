package com.davinchicoder.spring.mongo.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.davinchicoder.spring.mongo.infrastructure")
@EnableMongoAuditing
public class AppConfig {
}
