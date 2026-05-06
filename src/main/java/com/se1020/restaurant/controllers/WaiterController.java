package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.Waiter;
import com.se1020.restaurant.models.Customer;
import com.se1020.restaurant.models.AreaReservation;
import com.se1020.restaurant.models.TableReservation;
import com.se1020.restaurant.repositories.WaiterRepository;
import com.se1020.restaurant.repositories.CustomerRepository;
import com.se1020.restaurant.repositories.AreaReservationRepository;
import com.se1020.restaurant.repositories.TableReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waiters")
@CrossOrigin(origins = "*")
public class WaiterController {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AreaReservationRepository areaReservationRepository;

    @Autowired
    private TableReservationRepository tableReservationRepository;

    @GetMapping
    public List<Waiter> getAllWaiters() {
        return waiterRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Waiter> createWaiter(@RequestBody Waiter waiter) {
        if (waiter.getId() != null) {
            java.util.Optional<Waiter> existingWaiterOpt = waiterRepository.findById(waiter.getId());
            if (existingWaiterOpt.isPresent()) {
                Waiter existingWaiter = existingWaiterOpt.get();

                // Look up the linked fake Customer using the OLD email before we change it
                String oldFakeEmail = "customer." + existingWaiter.getEmail();
                Optional<Customer> fakeCustomerOpt = customerRepository.findByEmail(oldFakeEmail);

                existingWaiter.setUsername(waiter.getUsername());
                existingWaiter.setEmail(waiter.getEmail());
                if (waiter.getPassword() != null && !waiter.getPassword().isEmpty()) {
                    existingWaiter.setPassword(waiter.getPassword());
                }
                Waiter updatedWaiter = waiterRepository.save(existingWaiter);

                // Sync the fake Customer profile with the updated Waiter details
                if (fakeCustomerOpt.isPresent()) {
                    Customer fakeCustomer = fakeCustomerOpt.get();
                    fakeCustomer.setUsername("customer-" + updatedWaiter.getUsername());
                    fakeCustomer.setEmail("customer." + updatedWaiter.getEmail());
                    if (waiter.getPassword() != null && !waiter.getPassword().isEmpty()) {
                        fakeCustomer.setPassword(updatedWaiter.getPassword());
                    }
                    customerRepository.save(fakeCustomer);
                }

                return ResponseEntity.ok(updatedWaiter);
            }
        }
        if (waiter.getEmployeeId() == null || waiter.getEmployeeId().isEmpty()) {
            // Auto generate ID if not provided
            waiter.setEmployeeId("W-" + (100 + waiterRepository.count()));
        }

        Waiter savedWaiter = waiterRepository.save(waiter);

        // Create fake Customer for the waiter
        Customer fakeCustomer = new Customer();
        fakeCustomer.setUsername("customer-" + savedWaiter.getUsername());
        fakeCustomer.setEmail("customer." + savedWaiter.getEmail());
        fakeCustomer.setPassword(savedWaiter.getPassword());
        fakeCustomer.setPhoneNumber("-");
        fakeCustomer.setLoyaltyPoints(0);
        customerRepository.save(fakeCustomer);

        return ResponseEntity.ok(savedWaiter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWaiter(@PathVariable Integer id) {
        return waiterRepository.findById(id).map(w -> {
            // Find and delete the associated fake customer and their reservations
            String fakeEmail = "customer." + w.getEmail();
            Optional<Customer> fakeCustomerOpt = customerRepository.findByEmail(fakeEmail);

            if (fakeCustomerOpt.isPresent()) {
                Customer fakeCustomer = fakeCustomerOpt.get();

                List<AreaReservation> areaRes = areaReservationRepository.findByCustomerId(fakeCustomer.getId());
                areaReservationRepository.deleteAll(areaRes);

                List<TableReservation> tableRes = tableReservationRepository.findByCustomerId(fakeCustomer.getId());
                tableReservationRepository.deleteAll(tableRes);

                customerRepository.delete(fakeCustomer);
            }

            waiterRepository.delete(w);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
