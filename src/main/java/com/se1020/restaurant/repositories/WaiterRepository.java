package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Integer> {
    Optional<Waiter> findByEmail(String email);
}
