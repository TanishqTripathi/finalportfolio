package com.portfolio.website.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.website.Model.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    //
    // This interface will automatically provide CRUD operations for the Project
    // entity
    // No additional methods are needed unless custom queries are required
}
