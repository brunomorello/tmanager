package br.com.bmo.tmanager.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    public @Bean MongoClient mongoClient() {
        return MongoClients.create("mongodb://taskmanager-mongodb-dev:hXyDRUKF25KZhoFrbnnIb2OujFThW40YnjPm4be5hokamwUNQkbjjHR0bpEQ15Jwdj6gTosgT2hXZuzJ13iRXw==@taskmanager-mongodb-dev.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@taskmanager-mongodb-dev@");
    }
}
