package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.RestaurantArea;
import com.se1020.restaurant.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "*")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping
    public ResponseEntity<List<RestaurantArea>> getAllAreas() {
        return ResponseEntity.ok(areaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<RestaurantArea> createArea(@RequestBody RestaurantArea area) {
        if (area.getId() != null) {
            java.util.Optional<RestaurantArea> existingAreaOpt = areaRepository.findById(area.getId());
            if (existingAreaOpt.isPresent()) {
                RestaurantArea existingArea = existingAreaOpt.get();
                existingArea.setName(area.getName());
                existingArea.setReservable(area.isReservable());
                return ResponseEntity.ok(areaRepository.save(existingArea));
            }
        }
        return ResponseEntity.ok(areaRepository.save(area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable Integer id) {
        return areaRepository.findById(id).map(area -> {
            areaRepository.delete(area);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
