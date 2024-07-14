package com.example.project_Assignment.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UrlMapping {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortUrl;
    private String longUrl;
    private LocalDateTime expiryDate;
    
    public UrlMapping() {
    }

    public UrlMapping(String shortUrl, String longUrl, LocalDateTime expiryDate) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.expiryDate = expiryDate;
    }
}
