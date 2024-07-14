package com.example.project_Assignment.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project_Assignment.Entity.UrlMapping;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortUrl(String shortUrl);
    boolean existsByShortUrl(String shortUrl);
    void deleteByExpiryDateBefore(LocalDateTime now);
}