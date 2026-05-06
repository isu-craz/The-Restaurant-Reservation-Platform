package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.BaseReservation;
import com.se1020.restaurant.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*") // Allows the static HTML files to call this API
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // DTO class for receiving the JSON payload
    public static class CreateReservationRequest {
        public Integer customerId;
        public String contactInfo;
        public String walkInName;
        public Integer waiterId;
        public Integer tableId;
        public Integer targetAreaId;
        public String date; // Format: YYYY-MM-DD
        public String time; // Format: HH:MM
        public int partySize;
        public Integer duration;
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationRequest request) {
        try {
            LocalDate date = LocalDate.parse(request.date);
            LocalTime time = LocalTime.parse(request.time);

            BaseReservation reservation = reservationService.createReservation(
                    request.customerId,
                    request.contactInfo,
                    request.walkInName,
                    request.waiterId,
                    request.tableId,
                    request.targetAreaId,
                    date,
                    time,
                    request.partySize,
                    request.duration != null ? request.duration : 2 // Default to 2 if null
            );
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BaseReservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateReservationStatus(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        try {
            String newStatus = body.get("status");
            String type = body.get("type"); // Need to know if it's TABLE or AREA
            if (newStatus == null) {
                return ResponseEntity.badRequest().body("Status is required in the JSON body");
            }
            if (type == null) type = "TABLE"; // Fallback for backwards compatibility

            Integer waiterId = null;
            if (body.get("waiterId") != null) {
                try {
                    waiterId = Integer.parseInt(body.get("waiterId"));
                } catch (NumberFormatException ignored) {}
            }

            BaseReservation updated = reservationService.updateReservationStatus(id, newStatus, type, waiterId);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id, @RequestParam(defaultValue = "TABLE") String type) {
        try {
            reservationService.deleteReservation(id, type);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

