package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.RestaurantTable;
import com.se1020.restaurant.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "*") // Allows the local HTML file to call this API without CORS errors
public class TableController {

    @Autowired
    private TableRepository tableRepository;

    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    @PostMapping
    public org.springframework.http.ResponseEntity<RestaurantTable> createTable(@RequestBody RestaurantTable table) {
        if (table.getId() != null) {
            java.util.Optional<RestaurantTable> existingTableOpt = tableRepository.findById(table.getId());
            if (existingTableOpt.isPresent()) {
                RestaurantTable existingTable = existingTableOpt.get();
                existingTable.setTableNumber(table.getTableNumber());
                existingTable.setSeatingCapacity(table.getSeatingCapacity());
                existingTable.setArea(table.getArea());
                return org.springframework.http.ResponseEntity.ok(tableRepository.save(existingTable));
            }
        }
        return org.springframework.http.ResponseEntity.ok(tableRepository.save(table));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<?> deleteTable(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        return tableRepository.findById(id).map(t -> {
            tableRepository.delete(t);
            return org.springframework.http.ResponseEntity.ok().build();
        }).orElse(org.springframework.http.ResponseEntity.notFound().build());
    }
}
