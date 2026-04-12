# 🧠 The Deep Dive: Architecture & OOP Component Explanations

To guarantee our team gets top marks for **Inheritance, Encapsulation, and Polymorphism**, we have carefully designed how our 6 core components connect mathematically. 

This document explains the technical "why" behind the scenario. Share this with your team members so they know exactly how to design their Java Models to satisfy the rubric!

---

### 1. User Management & Auth (Member 1)
*   **The Scenario:** John (Customer) and Sarah (Admin) use the platform.
*   **The OOP Strategy (Inheritance):** Member 1 does not just create a simple `User` class. They create a base `User` class containing the email and password variables. Then, they use **Inheritance** to create `Customer` and `Waiter` classes that `extend` the `User` class. This proves to the professor we know how to reuse code efficiently!
*   **The Tech:** Handles the `/api/auth` login APIs to secure the Single Page Application.

### 2. Dining Area Management (Member 2)
*   **The Scenario:** Sarah adds physical zones (Main Dining, VIP Lounge).
*   **The OOP Strategy (Encapsulation):** Member 2 manages the `RestaurantArea` model. They will cleanly encapsulate private area capacities and any base booking fees (so you can't book the VIP Lounge for free). These are stored in the SQLite database and served entirely via a JSON API.

### 3. Table Management (Member 3)
*   **The Scenario:** Sarah adds tables into specific Dining Areas.
*   **The OOP Strategy (Relational Links):** Member 3 manages the `RestaurantTable` model. They prove advanced database knowledge by linking their Table object back to Member 2's `RestaurantArea` using the `@ManyToOne` annotation. This ensures you can't magically place a table in an area that doesn't exist.

### 4. Reservation Management (Member 4)
*   **The Scenario:** John books a table online in advance.
*   **The OOP Strategy (Polymorphism & Inheritance):** This is the brain of the app! Member 4 creates a parent `Reservation` class. Then they create a `TableReservation` child class AND an `AreaReservation` child class (for booking out an entire floor!). If both child classes override a method called `calculateTotalCost()`, they instantly score the **Polymorphism** requirement on the rubric!

### 5. Review & Complaint Management (Member 5)
*   **The Scenario:** John leaves a 5-star review.
*   **The OOP Strategy (Logical Validation):** Member 5 manages the `Review` model. They must use advanced RestController logic to verify that John actually ate at the restaurant before inserting the review into the database. They will check Member 4's `TableReservation` status to ensure it says `COMPLETED` before accepting the POST request.

### 6. Walk-In & Live Occupancy (Member 6)
*   **The Scenario:** A Waiter immediately locks a table for a guest who walks in off the street.
*   **The OOP Strategy (Inheritance vs Duplication):** Instead of building a dangerous duplicate `walk_ins` database that would conflict with online bookings, Member 6 uses **Inheritance**. They create a `WalkInBooking` class that extends Member 4's `Reservation` class. 
*   **The Tech:** Member 6 also controls the Waiter Dashboard UI. Because all bookings (Online and Walkin) now live inside the same inherited tree, Member 6 can safely loop through them all via Javascript to color-code tables on the live Staff Dashboard without database collisions!

---

### In Summary
We are not writing scattered code. 
*   Member 1, 4, and 6 are cooperating to build an **Inheritance Tree**.
*   Member 2, 3, and 5 are supplying the **Relational Datapoints** those trees require.
*   All 6 members are using `@RestController` and `Vanilla.js` to build a unified Single Page Application.

If everyone correctly extends and connects their specific Java objects, the entire backend will magically click together!
