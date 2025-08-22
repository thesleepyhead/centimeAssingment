package com.example.service4.config;

import com.example.service4.service.CharacterClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private CharacterClassService characterClassService;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing database with character class data...");
        characterClassService.populateDatabase();
        log.info("Database initialization completed!");
    }
}
