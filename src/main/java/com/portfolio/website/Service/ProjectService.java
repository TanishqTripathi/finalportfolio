package com.portfolio.website.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.website.Model.Project;
import com.portfolio.website.Repository.ProjectRepo;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    public void likeproject(long id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setLikes(project.getLikes() + 1);
        projectRepo.save(project);
    }

    public Project getProjectById(long id) {
        return projectRepo.findById(id).orElse(null);
    }

    // @Override
    public boolean deleteProjectById(Long id) {
        if (projectRepo.existsById(id)) {
            projectRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
