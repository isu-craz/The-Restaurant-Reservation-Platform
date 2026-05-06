package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.Customer;
import com.se1020.restaurant.models.TableReservation;
import com.se1020.restaurant.models.AreaReservation;
import com.se1020.restaurant.repositories.CustomerRepository;
import com.se1020.restaurant.repositories.TableReservationRepository;
import com.se1020.restaurant.repositories.AreaReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TableReservationRepository tableReservationRepository;

    @Autowired
    private AreaReservationRepository areaReservationRepository;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }

    public static class UpdateRequest {
        public String name;
        public String email;
        public String phone;
        public String password;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody UpdateRequest request) {
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            Customer customer = opt.get();
            if (request.name != null && !request.name.isEmpty()) customer.setUsername(request.name);
            if (request.email != null && !request.email.isEmpty()) customer.setEmail(request.email);
            if (request.phone != null && !request.phone.isEmpty()) customer.setPhoneNumber(request.phone);
            if (request.password != null && !request.password.isEmpty()) customer.setPassword(request.password);

            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (!customerOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = customerOpt.get();

        // Need to delete dependencies first due to FK constraints
        // 1. Table Reservations
        List<TableReservation> tRes = tableReservationRepository.findAll().stream()
                .filter(r -> r.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
        tableReservationRepository.deleteAll(tRes);

        // 2. Area Reservations
        List<AreaReservation> aRes = areaReservationRepository.findAll().stream()
                .filter(r -> r.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
        areaReservationRepository.deleteAll(aRes);

        // Finally delete the customer
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}
