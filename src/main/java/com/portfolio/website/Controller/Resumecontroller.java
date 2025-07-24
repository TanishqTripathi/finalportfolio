package com.portfolio.website.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import jakarta.persistence.criteria.Path;

import org.springframework.http.*;

@RestController
@RequestMapping("/api")
public class Resumecontroller {
    private static final String RESUME_DIR = "src/main/resources/static/resume/";
    private static final String RESUME_FILE_NAME = "Tanishq Kumar Tripathi_resume2.pdf";

    @Value("${app.api.key}")
    private String apiKey;

    @PostMapping("/resume")
    public ResponseEntity<String> uploadResume(@RequestHeader("X-API-KEY") String key,
            @RequestParam("file") MultipartFile file) throws IOException {
        // Check API Key
        if (!apiKey.equals(key)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }

        // Validate file
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Only PDF files are allowed");
        }

        // Ensure resume directory exists
        Files.createDirectories(Paths.get(RESUME_DIR));

        // Save the file
        Path path = Paths.get(RESUME_DIR + RESUME_FILE_NAME);
        Files.write(path, file.getBytes());

        return ResponseEntity.ok("Resume uploaded successfully");
    }

    @GetMapping("/resume/public")
    public ResponseEntity<Resource> publicDownload() throws IOException {
        Resource file = new ClassPathResource("static/resume/Tanishq Kumar Tripathi_resume2.pdf");
        if (!file.exists()) {
            throw new FileNotFoundException("Resume not found");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Tanishq_Kumar_Tripathi_Resume.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

}
