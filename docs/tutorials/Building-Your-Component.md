# 🏗️ How to Build Component 4: Reservation Management (Leader Guide)

Because Component 4 is the massive brain of this application, you need to build it very methodically. This component is also responsible for guaranteeing your team gets full **Inheritance and Polymorphism** marks from the university!

Follow these 4 phases exactly to build your code.

---

## Phase 1: The "Fake" Table Stub
Because a `Reservation` requires a table to be booked, you need the `Table.java` class to exist. If Member 3 hasn't finished the Table component yet, you can create a "Stub" (a fake, temporary class) just so your code compiles.

Create this file in **`src/main/java/com/se1020/restaurant/models/Table.java`**:
```java
package com.se1020.restaurant.models;

import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "tables") // Renamed so it doesn't conflict with SQL keyword
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // You literally don't need to add anything else! Member 3 will finish this later.
    // Be sure to generate a Getter/Setter for the ID:
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
```

---

## Phase 2: The Core OOP Models (Your Grading Arsenal)

This is where you get the university marks! We are going to build an Interface, a Parent class, and a Child class.

### 1. The Interface (Polymorphism)
Create a file at **`src/main/java/com/se1020/restaurant/models/Discountable.java`**:
```java
package com.se1020.restaurant.models;

public interface Discountable {
    double applyDiscount(double basePrice);
}
```

### 2. The Parent Class (Inheritance)
Create **`src/main/java/com/se1020/restaurant/models/Reservation.java`**.
*Notice how we use `@ManyToOne` to link this reservation to your `User` and `Table`!*
```java
package com.se1020.restaurant.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Required so children save to SQL
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int partySize;
    private LocalDateTime bookingTime;
    
    // The magical links to other components!
    @ManyToOne
    private User customer;

    @ManyToOne
    private Table bookedTable;

    // TODO: Right click -> Generate -> Getters and Setters for all variables!
}
```

### 3. The Child Class (Inheritance + Polymorphism)
Create **`src/main/java/com/se1020/restaurant/models/VIPReservation.java`**.
*Notice how it `extends` Reservation and `implements` Discountable! This is exactly what your professor wants to see.*
```java
package com.se1020.restaurant.models;

import jakarta.persistence.Entity;

@Entity
public class VIPReservation extends Reservation implements Discountable {
    
    private String specialRequest; // Only VIPs get to make special requests!

    // Implement the Interface logic
    @Override
    public double applyDiscount(double basePrice) {
        // VIPs get 15% off their booking fee!
        return basePrice * 0.85; 
    }

    // TODO: Right click -> Generate -> Getters and Setters
}
```

---

## Phase 3: The Data Flow (SQLite Access)

Now you just need to tell Spring Boot to generate the SQLite queries for you.

Create **`src/main/java/com/se1020/restaurant/repositories/ReservationRepository.java`**:
```java
package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Spring Boot writes all the SQL instantly! You don't need to add anything.
}
```

---

## Phase 4: The Controller & HTML

Finally, create the Controller that handles the web traffic!

Create **`src/main/java/com/se1020/restaurant/controllers/ReservationController.java`**:
```java
package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.Reservation;
import com.se1020.restaurant.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    // When the JS fetch calls http://localhost:8080/api/reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
```

### The Front-End Step:
To finish your component, take the `customer-booking.html` code from the `docs/project-ui/html-prototypes/` folder, drop it into `src/main/resources/static/`, and use Vanilla JS `fetch()` to call your `@RestController` to save the data!
