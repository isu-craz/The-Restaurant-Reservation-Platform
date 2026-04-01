# 🎓 OOP Strategy Guide (Passing the Viva)

Your SE1020 university rubric explicitly mandates that your team MUST demonstrate **Encapsulation, Inheritance, and Polymorphism** during the final viva presentation. If your code is just basic Java, the examiner will penalize you.

Here is the exact strategy our team is going to use to easily secure these marks!

## 1. Encapsulation (Easy)
We are already doing this perfectly. In every Database Model (`User.java`, `Table.java`), all variables are marked as `private`. We ONLY access them publicly via `get()` and `set()` methods.

## 2. Inheritance (The Spring Boot Single-Table Trick)
To prove inheritance, we are going to use classic textbook OOP: A generic `Reservation` parent class, splitting into specific child types (like a `VIPReservation` and a `StandardReservation`). 

**Our Trick:**
In Spring Boot, we use `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`. This magically lets us keep just **ONE** clean `reservations` table in our SQLite database, but we get to split the Java code into 3 files to prove to the professor we can use inheritance flawlessly!

1. **The Parent:** `Reservation.java`
```java
package com.se1020.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "reservation_type") // This saves whether it is VIP or STANDARD
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // We put common variables here!
    private String date;
    private int guestCount;
}
```

2. **The Children:** `VIPReservation.java` and `StandardReservation.java`
```java
package com.se1020.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("VIP")
public class VIPReservation extends Reservation {
    // We automatically inherit the date and guestCount!
    // But we add custom things here just for VIPs:
    private boolean requiresChampagne;
}
```

## 3. Polymorphism (The Interface Trick)
Polymorphism means "many forms". The easiest way to prove you did this is to create a Java Interface with a method that multiple classes execute differently!

**Our Strategy:**
Create an interface called `Discountable.java`:
```java
public interface Discountable {
    double calculateFinalPrice(double originalPrice);
}
```

Then, Member 4 (who is building the **Reservations** component) implements this interface in their exact models:
1. `VIPReservation` implements `calculateFinalPrice` to charge a $50 extra seating fee.
2. `StandardReservation` implements `calculateFinalPrice` to charge no fee.

---
**TEAM MEMBER RULE:** Tell the examiner to look at the `Reservation` hierarchy (`extends Reservation`) and `Discountable.java` during your viva, and you will instantly secure your OOP points!
