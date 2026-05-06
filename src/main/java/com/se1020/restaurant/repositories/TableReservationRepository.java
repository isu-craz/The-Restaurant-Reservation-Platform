package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Integer> {

    @Query("SELECT r FROM TableReservation r WHERE r.reservationDate = :date AND r.table.id = :tableId AND r.status != 'CANCELLED'")
    List<TableReservation> findActiveReservationsForTableOnDate(LocalDate date, Integer tableId);

    @Query("SELECT r FROM TableReservation r WHERE r.reservationDate = :date AND r.table.area.id = :areaId AND r.status != 'CANCELLED'")
    List<TableReservation> findActiveTableReservationsInsideArea(LocalDate date, Integer areaId);

    List<TableReservation> findByCustomerId(Integer customerId);
}
