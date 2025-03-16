package com.hacom.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.hacom.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    private final MongoDBConfigProperties properties;

    public MongoConfig(MongoDBConfigProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(properties.getUri());
    }

    @Override
    protected String getDatabaseName() {
        return properties.getDatabase();
    }

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
            new OffsetDateTimeToStringConverter(),
            new StringToOffsetDateTimeConverter()
        ));
    }

}
