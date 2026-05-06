package com.se1020.restaurant.models;

import jakarta.persistence.*;

@Entity
@Table(name = "area_reservations")
public class AreaReservation extends BaseReservation {

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private RestaurantArea targetArea;

    @Transient // not saved to DB
    private String type = "AREA";

    public RestaurantArea getTargetArea() { return targetArea; }
    public void setTargetArea(RestaurantArea targetArea) { this.targetArea = targetArea; }

    public String getType() { return type; }
}

