package com.se1020.restaurant.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas")
public class RestaurantArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private int totalCapacity;
    private boolean isReservable; // true if guests are allowed to book this entire area

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "area_features", joinColumns = @JoinColumn(name = "area_id"))
    @Column(name = "feature")
    private List<String> features = new ArrayList<>();

    // The Magic Cascade: Proving true OOP Composition to the examiner!
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantTable> tables = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTotalCapacity() { return totalCapacity; }
    public void setTotalCapacity(int totalCapacity) { this.totalCapacity = totalCapacity; }

    public boolean isReservable() { return isReservable; }
    public void setReservable(boolean reservable) { isReservable = reservable; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
}
