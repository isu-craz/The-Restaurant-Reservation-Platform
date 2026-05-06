package com.se1020.restaurant.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@MappedSuperclass
public abstract class BaseReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int partySize;
    private String status; // e.g. "CONFIRMED", "CANCELLED"
    private Integer duration; // Duration in hours

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String walkInName;

    @ManyToOne
    @JoinColumn(name = "assigned_waiter_id", nullable = true)
    private Waiter assignedWaiter;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public LocalTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalTime reservationTime) { this.reservationTime = reservationTime; }

    public int getPartySize() { return partySize; }
    public void setPartySize(int partySize) { this.partySize = partySize; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getWalkInName() { return walkInName; }
    public void setWalkInName(String walkInName) { this.walkInName = walkInName; }

    public Waiter getAssignedWaiter() { return assignedWaiter; }
    public void setAssignedWaiter(Waiter assignedWaiter) { this.assignedWaiter = assignedWaiter; }
}
