package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "userDashboard";
    }
}