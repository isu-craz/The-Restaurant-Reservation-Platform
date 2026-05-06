package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.AreaReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AreaReservationRepository extends JpaRepository<AreaReservation, Integer> {

    @Query("SELECT r FROM AreaReservation r WHERE r.reservationDate = :date AND r.targetArea.id = :areaId AND r.status != 'CANCELLED'")
    List<AreaReservation> findActiveReservationsForAreaOnDate(LocalDate date, Integer areaId);

    List<AreaReservation> findByCustomerId(Integer customerId);
}
