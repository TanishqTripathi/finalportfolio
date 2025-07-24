package com.portfolio.website.Controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.website.Model.Project;
import com.portfolio.website.Service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")

public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Value("${app.api.key}")
    private String apiKey;

    @GetMapping("/t")
    public String testApiKey() {
        return "Configured API Key: " + apiKey;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveProject(
            @Valid @RequestBody Project project,
            @RequestHeader("X-API-KEY") String requestApiKey) {

        if (!apiKey.equals(requestApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }

        Project savedProject = projectService.saveProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/like")
    public void likeProject(@PathVariable Long id) {
        projectService.likeproject(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Long id,
            @RequestHeader("X-API-KEY") String RequestapiKey) {

        if (!apiKey.equals(RequestapiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }

        boolean deleted = projectService.deleteProjectById(id);
        if (deleted) {
            return ResponseEntity.ok("Project deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long id,
            @RequestBody @Valid Project updatedProject,
            @RequestHeader("X-API-KEY") String RequestapiKey) {

        if (!apiKey.equals(RequestapiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Project existingProject = projectService.getProjectById(id);
        if (existingProject == null) {
            return ResponseEntity.notFound().build();
        }

        updatedProject.setId(id); // Ensure ID stays the same
        Project saved = projectService.saveProject(updatedProject);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/projects")
    public String showProjects(Model model) {
        List<Project> allProjects = projectService.getAllProjects();
        model.addAttribute("projects", allProjects);
        return "projects";
    }

}
