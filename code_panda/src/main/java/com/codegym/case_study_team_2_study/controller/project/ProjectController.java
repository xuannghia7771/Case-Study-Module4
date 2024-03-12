package com.codegym.case_study_team_2_study.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProjectController {
    @GetMapping("/gallery")
    public String showGallery() {
        return "/gallery";
    }

    @GetMapping("/firepage")
    public String showFormFirePage() {
        return "/firepage-create";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "/about";
    }

    @GetMapping("/class")
    public String showClass() {
        return "/class";
    }

    @GetMapping("/blog")
    public String showBlog() {
        return "/blog";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "/contact";
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/single")
    public String showSingle() {
        return "/single";
    }

    @GetMapping("/team")
    public String showTeam() {
        return "/team";
    }

    @GetMapping("/success")
    public String showLandingPage() {
        return "/landing_page";
    }
    @GetMapping("/403")
    public String showError(){
    return "/403";
    }
}
