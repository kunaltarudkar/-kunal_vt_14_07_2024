package com.example.project_Assignment.Controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_Assignment.service.UrlShortenerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String, String> request) {
        return urlShortenerService.shortenUrl(request.get("destinationUrl"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateShortUrl(@RequestBody Map<String, String> request) {
        return urlShortenerService.updateShortUrl(request.get("shortUrl"), request.get("destinationUrl"));
    }

    @GetMapping("/{shortenString}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString) throws IOException {
        urlShortenerService.redirectToFullUrl(response, shortenString);
    }

    @PostMapping("/updateExpiry")
    public ResponseEntity<?> updateExpiry(@RequestBody Map<String, Object> request) {
        return urlShortenerService.updateExpiry((String) request.get("shortUrl"), (int) request.get("daysToAdd"));
    }
}
