package com.example.config;

import com.example.repository.BookRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = BookRepository.class)
public class BookRepositoryConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "bookstore";
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.example.domain";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
            MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
            .build());

    }
}