package edu.du.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.common", "com.example.web"})
public class PortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalWebApplication.class, args);
    }
} 