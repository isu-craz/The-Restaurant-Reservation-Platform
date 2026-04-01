package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.User;
import com.se1020.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list-users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "users/add-user";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        // Enforce the default role so we don't crash
        user.setRole("CUSTOMER");
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        User existingUser = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", existingUser);
        return "users/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        userRepository.save(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}