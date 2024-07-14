package com.example.project_Assignment.util;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.project_Assignment.Repository.UrlMappingRepository;

@Service
public class ShortUrlGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 8;
    private static final Random RANDOM = new Random();

    public static String generateUniqueShortUrl(UrlMappingRepository urlMappingRepository) {
        String shortUrl;

        do {
            shortUrl = generateShortUrl();
        } while (urlMappingRepository.existsByShortUrl(shortUrl));

        return shortUrl;
    }

    private static String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return shortUrl.toString();
    }
}
