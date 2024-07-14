package com.example.project_Assignment.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
@Service
public interface UrlShortenerService {

    ResponseEntity<?> shortenUrl(String longUrl);

    ResponseEntity<?> updateShortUrl(String shortUrl, String newLongUrl);

    void redirectToFullUrl(HttpServletResponse response, String shortUrl) throws IOException;

    ResponseEntity<?> updateExpiry(String shortUrl, int daysToAdd);
}