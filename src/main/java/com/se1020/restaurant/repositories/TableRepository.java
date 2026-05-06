package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {
    List<RestaurantTable> findByArea_Name(String name);
}
