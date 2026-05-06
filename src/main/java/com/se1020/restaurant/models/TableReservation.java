package com.se1020.restaurant.models;

import jakarta.persistence.*;

@Entity
@Table(name = "table_reservations")
public class TableReservation extends BaseReservation {

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    // We also want to serialize the type so the frontend can easily distinguish if it wants to
    @Transient // not saved to DB
    private String type = "TABLE";

    public RestaurantTable getTable() { return table; }
    public void setTable(RestaurantTable table) { this.table = table; }

    public String getType() { return type; }
}
