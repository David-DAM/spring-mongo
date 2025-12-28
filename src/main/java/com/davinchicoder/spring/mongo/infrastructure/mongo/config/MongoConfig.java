package com.davinchicoder.spring.mongo.infrastructure.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.davinchicoder.spring.mongo.infrastructure.mongo")
@EnableMongoAuditing
@Configuration
public class MongoConfig {
}
