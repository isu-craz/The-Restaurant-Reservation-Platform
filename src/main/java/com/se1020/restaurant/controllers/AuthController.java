package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.Customer;
import com.se1020.restaurant.models.Waiter;
import com.se1020.restaurant.models.Admin;
import com.se1020.restaurant.repositories.CustomerRepository;
import com.se1020.restaurant.repositories.WaiterRepository;
import com.se1020.restaurant.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private AdminRepository adminRepository;

    public static class LoginRequest {
        public String email;
        public String password;
        public String role;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.role != null && request.role.equals("CUSTOMER")) {
            Optional<Customer> cust = customerRepository.findByEmail(request.email);
            if (cust.isPresent() && cust.get().getPassword().equals(request.password)) {
                return ResponseEntity.ok(buildPayload(cust.get().getId(), cust.get().getUsername(), cust.get().getEmail(), "CUSTOMER"));
            }
        }

        if (request.role != null && request.role.equals("WAITER")) {
            Optional<Waiter> wait = waiterRepository.findByEmail(request.email);
            if (wait.isPresent() && wait.get().getPassword().equals(request.password)) {
                return ResponseEntity.ok(buildPayload(wait.get().getId(), wait.get().getUsername(), wait.get().getEmail(), "WAITER"));
            }
        }

        if (request.role != null && request.role.equals("ADMIN")) {
            Optional<Admin> adm = adminRepository.findByEmail(request.email);
            if (adm.isPresent() && adm.get().getPassword().equals(request.password)) {
                return ResponseEntity.ok(buildPayload(adm.get().getId(), adm.get().getUsername(), adm.get().getEmail(), "ADMIN"));
            }
        }

        return ResponseEntity.status(401).body("Invalid credentials for this role");
    }

    private Map<String, Object> buildPayload(Integer id, String username, String email, String role) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("username", username);
        payload.put("email", email);
        payload.put("role", role);
        return payload;
    }

    public static class SignupRequest {
        public String name;
        public String phone;
        public String email;
        public String password;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (customerRepository.findByEmail(request.email).isPresent() ||
                waiterRepository.findByEmail(request.email).isPresent() ||
                adminRepository.findByEmail(request.email).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Customer customer = new Customer();
        customer.setUsername(request.name);
        customer.setPhoneNumber(request.phone);
        customer.setEmail(request.email);
        customer.setPassword(request.password);
        customer.setLoyaltyPoints(0);

        customerRepository.save(customer);

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", customer.getId());
        payload.put("username", customer.getUsername());
        payload.put("email", customer.getEmail());
        payload.put("role", "CUSTOMER");

        return ResponseEntity.ok(payload);
    }
}
