package com.example.project_Assignment.service.Impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project_Assignment.Entity.UrlMapping;
import com.example.project_Assignment.Repository.UrlMappingRepository;
import com.example.project_Assignment.service.UrlShortenerService;
import com.example.project_Assignment.util.ShortUrlGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Override
    public ResponseEntity<?> shortenUrl(String longUrl) {
        if (longUrl == null || longUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }

        String shortUrl = ShortUrlGenerator.generateUniqueShortUrl(urlMappingRepository);
        LocalDateTime expiryDate = LocalDateTime.now().plusMonths(10);

        UrlMapping urlMapping = new UrlMapping(shortUrl, longUrl, expiryDate);
        urlMappingRepository.save(urlMapping);

        Map<String, Object> response = new HashMap<>();
        response.put("shortUrl", "http://localhost:8080/" + shortUrl);
        response.put("id", urlMapping.getId());
        response.put("success", true);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> updateShortUrl(String shortUrl, String newLongUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Short URL not found");
        }

        urlMapping.setLongUrl(newLongUrl);
        urlMappingRepository.save(urlMapping);

        return ResponseEntity.ok(Map.of("success", true));
    }

    @Override
    public void redirectToFullUrl(HttpServletResponse response, String shortUrl) throws IOException {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping == null || urlMapping.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new NoSuchElementException("URL not found or expired");
        }

        response.sendRedirect(urlMapping.getLongUrl());
    }

    @Override
    public ResponseEntity<?> updateExpiry(String shortUrl, int daysToAdd) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Short URL not found");
        }

        urlMapping.setExpiryDate(urlMapping.getExpiryDate().plusDays(daysToAdd));
        urlMappingRepository.save(urlMapping);

        return ResponseEntity.ok(Map.of("success", true));
    }
}