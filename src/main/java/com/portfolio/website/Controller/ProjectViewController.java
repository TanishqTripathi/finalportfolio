package com.portfolio.website.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.portfolio.website.Service.ProjectService;

@Controller
@RequestMapping("/view")
public class ProjectViewController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public String showProjects(Model model) {
        System.out.println("DEBUG: /view/projects called");
        model.addAttribute("projects", projectService.getAllProjects());
        return "Projects";
    }

    @GetMapping("/")
    public String indexView() {
        return "Home"; // file: index.html
    }

    @GetMapping("/Home")
    public String HomeView() {
        return "Home"; // file: Home.html
    }

    @GetMapping("/Resume")
    public String ResumeView() {
        return "Resume"; // file: Home.html
    }

    @GetMapping("/Contact")
    public String ContactView() {
        return "Contact"; // file: Home.html
    }

    @GetMapping("/About")
    public String AboutView() {
        return "About"; // file: Home.html
    }

    // @GetMapping("/Project")
    // public String ProjectView() {
    // return "Projects"; // file: Home.html
    // }

    // public ProjectViewController(ProjectService projectService) {
    // this.projectService = projectService;
    // }

    // @GetMapping("/projects")
    // public String showProjects(Model model) {
    // model.addAttribute("projects", projectService.getAllProjects());
    // return "Projects"; // resolves to project.html
    // }

    // @GetMapping("/projects")
    // public ModelAndView showProjects() {
    // ModelAndView modelAndView = new ModelAndView("Projects"); // View name
    // modelAndView.addObject("projects", projectService.getAllProjects()); // Data
    // return modelAndView;
    // }
}
