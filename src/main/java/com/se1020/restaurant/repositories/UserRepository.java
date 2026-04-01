package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Spring Boot writes all the SQL automatically!
}