# Restaurant Table Reservation Platform - Workload Distribution Plan

This document outlines the workload distribution for a team of 6 members building a "Restaurant Table Reservation Platform" using Java, Spring Boot, HTML/CSS, and file handling. 

This plan aligns with the project requirements of implementing at least 3 CRUD operations per member using OOP concepts.

### Proposed Components (SQLite Database Strategy)

---

### Component 1: User Management & Authentication (👤 Member 1)
**Description:** Manages the registration, login, and profiles of all platform users.
**CRUD Operations (Table: `users`):**
*   **Create:** A user registers a new account. The System creates a hardcoded Admin on startup.
*   **Read:** The platform checks credentials during login or views a profile.
*   **Update:** A user changes their password or contact info.
*   **Delete:** A user deletes their account from the platform.
**OOP Concepts:**
*   **Encapsulation:** Secure user data (like passwords) using a `User` class.
*   **Security:** Utilizing a `Role` string (e.g. "CUSTOMER" or "ADMIN") to grant different access levels.
**UI Components:** Central Login Page, Registration Page, Profile Settings.

---

### Component 2: Dining Area Management (👤 Member 2)
**Description:** Allows the System Admin to create distinct physical zones within the restaurant.
**CRUD Operations (Table: `dining_areas`):**
*   **Create:** Admin creates a new "VIP Lounge" or "Outdoor Patio".
*   **Read:** Customers view the different areas when deciding where to book.
*   **Update:** Admin changes an area to "Smoking Allowed" or updates the booking fee.
*   **Delete:** Admin temporarily closes an area for renovation.
**OOP Concepts:**
*   **Encapsulation:** `DiningArea` class storing spatial logic and fees.
*   **Relationships:** A `DiningArea` has a `@OneToMany` relationship with `Table`s.
**UI Components:** Add Dining Area form, View All Areas.

---

### Component 3: Table Management (👤 Member 3)
**Description:** Handles the physical tables placed inside specific Dining Areas.
**CRUD Operations (Table: `tables`):**
*   **Create:** Admin adds a new table (e.g., Table 5, Capacity 4).
*   **Read:** Customers view table capacities.
*   **Update:** Admin changes a table's maximum seating capacity.
*   **Delete:** Remove a broken table from the floor plan.
**OOP Concepts:**
*   **Encapsulation:** `Table` class storing sizes and IDs.
*   **Relationships:** A `Table` has a `@ManyToOne` link to a `DiningArea`.
**UI Components:** Add Table Form, Floorplan View for Admins.

---

### Component 4: Reservation Management (👤 Member 4)
**Description:** The core engine linking authenticated Users to Tables for a specific date and time.
**CRUD Operations (Table: `reservations`):**
*   **Create:** Customer creates an online booking online.
*   **Read:** Customer views upcoming bookings; Admin views today's schedule.
*   **Update:** Customer modifies reservation time or party size.
*   **Delete:** Customer cancels the reservation.
**OOP Concepts:**
*   **Encapsulation:** `Reservation` class holding date/time securely.
*   **Inheritance:** `VIPReservation` and `StandardReservation` inherit from a generic `Reservation` Parent Class to apply different processing fees.
**UI Components:** Make Booking Form, Availability Calendar, My Bookings Page.

---

### Component 5: Review & Complaint Management (👤 Member 5)
**Description:** Captures customer feedback for the overall restaurant experience.
**CRUD Operations (Table: `reviews`):**
*   **Create:** Customer gives a star rating and comment after a meal.
*   **Read:** Any user can view recent reviews prominently displayed.
*   **Update:** Customer edits a typo in their review.
*   **Delete:** Admin removes an abusive or spam review.
**OOP Concepts:**
*   **Encapsulation:** `Review` class holding integer ratings and text.
*   **Relationships:** Uses `@ManyToOne` linking a `User` profile to their `Review`.
**UI Components:** Submit Review Form, Testimonial View.

---

### Component 6: Walk-In & Live Occupancy (👤 Member 6)
**Description:** Overcomes the "Double-Booking" problem by allowing Admins to lock tables for spontaneous guests that arrive without an online account.
**CRUD Operations (Table: `walk_ins`):**
*   **Create:** Admin sits a physical family down and assigns them a table locking it in real time.
*   **Read:** Admin views a dashboard of currently occupied Walk-In tables.
*   **Update:** Admin updates the Walk-in party size if more friends arrive.
*   **Delete:** Admin clicks "Checkout" which deletes the Walk-in and frees the table for online bookings.
**OOP Concepts:**
*   **Polymorphism:** A `WalkIn` class bypassing the standard `User` requirement, heavily leveraging decoupled Foreign Keys (`@ManyToOne` with `tables`).
**UI Components:** Master FOH (Front-Of-House) iPad Dashboard, Lock Table Form.

---

## Verification Plan

Since this step is purely drafting the project distribution plan for team agreement, no code is being verified yet.

### Manual Verification
1. The user will review the components above to ensure they meet the 6-member team requirement.
2. The user will confirm the proposed SQL CRUD operations and OOP applications fit the project rubric requirements.
