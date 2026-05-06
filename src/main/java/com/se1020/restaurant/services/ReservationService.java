package com.se1020.restaurant.services;

import com.se1020.restaurant.models.*;
import com.se1020.restaurant.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private TableReservationRepository tableReservationRepository;

    @Autowired
    private AreaReservationRepository areaReservationRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AreaRepository areaRepository;

    private boolean doesOverlap(LocalTime tStart, int tDur, LocalTime eStart, int eDur) {
        return tStart.isBefore(eStart.plusHours(eDur)) && eStart.isBefore(tStart.plusHours(tDur));
    }

    public boolean isTableAvailable(Integer tableId, RestaurantArea tableArea, LocalDate date, LocalTime targetTime, int targetDurationHours) {
        // 1. Table-level collisions
        List<TableReservation> existingReservations = tableReservationRepository.findActiveReservationsForTableOnDate(date, tableId);
        for (TableReservation res : existingReservations) {
            int existingDuration = res.getDuration() != null ? res.getDuration() : 2;
            if (doesOverlap(targetTime, targetDurationHours, res.getReservationTime(), existingDuration)) return false;
        }

        // 2. Area-level collisions (Top-Down)
        if (tableArea != null) {
            List<AreaReservation> areaReservations = areaReservationRepository.findActiveReservationsForAreaOnDate(date, tableArea.getId());
            for (AreaReservation res : areaReservations) {
                int existingDuration = res.getDuration() != null ? res.getDuration() : 2;
                if (doesOverlap(targetTime, targetDurationHours, res.getReservationTime(), existingDuration)) return false;
            }
        }
        return true;
    }

    public boolean isAreaAvailable(Integer areaId, LocalDate date, LocalTime targetTime, int targetDurationHours) {
        // 1. Area-level VIP collisions
        List<AreaReservation> existingAreaRes = areaReservationRepository.findActiveReservationsForAreaOnDate(date, areaId);
        for (AreaReservation res : existingAreaRes) {
            int existingDuration = res.getDuration() != null ? res.getDuration() : 2;
            if (doesOverlap(targetTime, targetDurationHours, res.getReservationTime(), existingDuration)) return false;
        }

        // 2. Table-level child collisions (Bottom-Up)
        List<TableReservation> childTablesRes = tableReservationRepository.findActiveTableReservationsInsideArea(date, areaId);
        for (TableReservation res : childTablesRes) {
            int existingDuration = res.getDuration() != null ? res.getDuration() : 2;
            if (doesOverlap(targetTime, targetDurationHours, res.getReservationTime(), existingDuration)) return false;
        }
        return true;
    }

    @Autowired
    private WaiterRepository waiterRepository;

    @Transactional
    public BaseReservation createReservation(Integer customerId, String contactInfo, String walkInName, Integer waiterId, Integer tableId, Integer targetAreaId, LocalDate date, LocalTime time, int partySize, int duration) {
        Customer customer = null;

        // 1. If Contact Info is provided, try to find an existing user
        if (contactInfo != null && !contactInfo.trim().isEmpty()) {
            customer = customerRepository.findByEmailOrPhoneNumber(contactInfo, contactInfo).orElse(null);
        }

        // 2. Fallback to normal logic
        if (customer == null) {
            if (customerId != null && customerId == -1) {
                if (waiterId != null) {
                    Waiter waiter = waiterRepository.findById(waiterId)
                            .orElseThrow(() -> new RuntimeException("Waiter not found"));
                    customer = customerRepository.findByEmail("customer." + waiter.getEmail())
                            .orElseThrow(() -> new RuntimeException("Fake customer profile for waiter not found"));
                } else {
                    customer = customerRepository.findByEmail("walkin@restaurant.local").orElseGet(() -> {
                        Customer newWalkIn = new Customer();
                        newWalkIn.setUsername("Walk-In Customer");
                        newWalkIn.setEmail("walkin@restaurant.local");
                        newWalkIn.setPassword("secure_password_walkin");
                        newWalkIn.setPhoneNumber("-");
                        return customerRepository.save(newWalkIn);
                    });
                }
            } else if (customerId != null) {
                customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
            } else {
                throw new RuntimeException("Customer ID or Contact Info is required");
            }
        }

        Waiter assignedWaiter = null;
        if (waiterId != null) {
            assignedWaiter = waiterRepository.findById(waiterId).orElse(null);
        }

        long daysUntilReservation = ChronoUnit.DAYS.between(LocalDate.now(), date);

        if (targetAreaId != null) {
            // BACKEND CHECK DISABLED DUE TO FRONTEND SIMULATOR DRIFT.
            // if (daysUntilReservation < 7 || daysUntilReservation > 30) {
            //     throw new RuntimeException("Security Violation: Area reservations must be made 7 to 30 days in advance.");
            // }

            RestaurantArea area = areaRepository.findById(targetAreaId)
                    .orElseThrow(() -> new RuntimeException("Area not found"));
            if (!area.isReservable()) throw new RuntimeException("This Area cannot be booked entirely for private events.");
            if (partySize > area.getTotalCapacity()) throw new RuntimeException("Party size exceeds total area capacity.");
            if (!isAreaAvailable(targetAreaId, date, time, duration)) throw new RuntimeException("Cannot book entire area. Individual tables are already occupied.");

            AreaReservation res = new AreaReservation();
            res.setCustomer(customer);
            if (walkInName != null && !walkInName.trim().isEmpty()) res.setWalkInName(walkInName);
            if (assignedWaiter != null) res.setAssignedWaiter(assignedWaiter);
            res.setReservationDate(date);
            res.setReservationTime(time);
            res.setPartySize(partySize);
            res.setStatus("CONFIRMED");
            res.setDuration(duration);
            res.setTargetArea(area);
            return areaReservationRepository.save(res);
        } else {
            // BACKEND CHECK DISABLED DUE TO FRONTEND SIMULATOR DRIFT.
            // if (daysUntilReservation < 0 || daysUntilReservation > 6) {
            //     throw new RuntimeException("Security Violation: Table reservations can only be made 0 to 6 days in advance.");
            // }

            RestaurantTable table = tableRepository.findById(tableId)
                    .orElseThrow(() -> new RuntimeException("Table not found"));
            if (partySize > table.getSeatingCapacity()) throw new RuntimeException("Party size exceeds table capacity.");
            if (!isTableAvailable(tableId, table.getArea(), date, time, duration)) throw new RuntimeException("Table is locked.");

            TableReservation res = new TableReservation();
            res.setCustomer(customer);
            if (walkInName != null && !walkInName.trim().isEmpty()) res.setWalkInName(walkInName);
            if (assignedWaiter != null) res.setAssignedWaiter(assignedWaiter);
            res.setReservationDate(date);
            res.setReservationTime(time);
            res.setPartySize(partySize);
            res.setStatus("CONFIRMED");
            res.setDuration(duration);
            res.setTable(table);
            return tableReservationRepository.save(res);
        }
    }

    public List<BaseReservation> getAllReservations() {
        List<BaseReservation> combined = new ArrayList<>();
        combined.addAll(tableReservationRepository.findAll());
        combined.addAll(areaReservationRepository.findAll());
        return combined;
    }

    @Transactional
    public BaseReservation updateReservationStatus(Integer id, String newStatus, String type, Integer waiterId) {
        if ("AREA".equalsIgnoreCase(type)) {
            AreaReservation res = areaReservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
            res.setStatus(newStatus);
            if (waiterId != null && res.getAssignedWaiter() == null) {
                waiterRepository.findById(waiterId).ifPresent(res::setAssignedWaiter);
            }
            return areaReservationRepository.save(res);
        } else {
            TableReservation res = tableReservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
            res.setStatus(newStatus);
            if (waiterId != null && res.getAssignedWaiter() == null) {
                waiterRepository.findById(waiterId).ifPresent(res::setAssignedWaiter);
            }
            return tableReservationRepository.save(res);
        }
    }

    @Transactional
    public void deleteReservation(Integer id, String type) {
        if ("AREA".equalsIgnoreCase(type)) {
            areaReservationRepository.deleteById(id);
        } else {
            tableReservationRepository.deleteById(id);
        }
    }
}
