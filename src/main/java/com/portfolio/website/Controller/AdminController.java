package com.portfolio.website.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Value("${app.api.key}")
    private String apiKey;

    @GetMapping("/test-key")
    public String testApiKey() {
        return "Configured API Key: " + apiKey;
    }
}
