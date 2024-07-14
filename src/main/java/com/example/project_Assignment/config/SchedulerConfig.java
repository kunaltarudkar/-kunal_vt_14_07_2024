package com.example.project_Assignment.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.project_Assignment.Repository.UrlMappingRepository;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Scheduled(fixedRate = 86400000) // Run once a day
    public void deleteExpiredUrls() {
        LocalDateTime now = LocalDateTime.now();
        urlMappingRepository.deleteByExpiryDateBefore(now);
    }
}
