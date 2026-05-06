package com.se1020.restaurant.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int seatingCapacity;
    private String tableNumber;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private RestaurantArea area;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "table_features", joinColumns = @JoinColumn(name = "table_id"))
    @Column(name = "feature")
    private List<String> features = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public int getSeatingCapacity() { return seatingCapacity; }
    public void setSeatingCapacity(int seatingCapacity) { this.seatingCapacity = seatingCapacity; }

    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }

    public RestaurantArea getArea() { return area; }
    public void setArea(RestaurantArea area) { this.area = area; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
}
