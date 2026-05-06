package com.se1020.restaurant.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "forward:/login.html";
    }

    @GetMapping({"/waiter", "/waiter/{tab}", "/waiter-dashboard", "/waiter-dashboard/{tab}"})
    public String waiterDashboard() {
        return "forward:/waiter.html";
    }

    @GetMapping({"/admin", "/admin/{tab}", "/admin-dashboard", "/admin-dashboard/{tab}"})
    public String adminDashboard() {
        return "forward:/admin.html";
    }

    @GetMapping({"/customer", "/customer/{tab}", "/customer-dashboard", "/customer-dashboard/{tab}"})
    public String customerDashboard() {
        return "forward:/customer.html";
    }

}
