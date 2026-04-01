# Understanding the 6 Components (A Restaurant Scenario)

To help the team understand how our 6 components work together to build a complete application, here is a real-world scenario of users and staff interacting with our **Restaurant Platform**:

Imagine three people: **John** (a Customer), **Sarah** (the Restaurant Manager/Admin), and **Mike** (a Walk-In Guest).

### 1. User Management & Auth (Member 1)
*   **What happens:** John visits your website to create a Customer account. Sarah uses a secure admin login provided by the system.
*   **Why it matters:** Member 1 writes the code that saves their data to the `users` database table securely. They handle the login page and use the specific `role` string so the website knows who is a Customer and who is an Admin!

### 2. Dining Area Management (Member 2)
*   **What Sarah does:** Sarah logs into her Admin panel and clicks "Manage Layout." She adds a "Main Dining Room", a "VIP Lounge", and an "Outdoor Patio".
*   **Why it matters:** Member 2 writes the code that saves these physical zones into the `dining_areas` database table so tables can be grouped logically.

### 3. Table Management (Member 3)
*   **What Sarah does:** Now that the VIP Lounge exists, Sarah clicks "Manage Tables" and adds five 2-person tables into the VIP Lounge.
*   **Why it matters:** Member 3 writes the code that saves these physical objects into the `tables` database table, securely linking each Table to Member 2's Dining Area using a Database Foreign Key (`@ManyToOne`).

### 4. Reservation Management (Member 4)
*   **What John does:** John logs in, browses the available tables for Friday night, and clicks "Book Table" for his anniversary in the VIP Lounge.
*   **Why it matters:** This is the core feature. Member 4 pulls data from Member 1 (John) and Member 3 (The Table) to create a brand new booking in the `reservations` database table!

### 5. Review & Complaint Management (Member 5)
*   **What John does:** On Saturday morning, John logs in and clicks "Leave Feedback." He gives the restaurant 5 stars and writes, *"Best anniversary ever!"*
*   **Why it matters:** Member 5 writes the code that captures John's review and attaches it to his user profile in the `reviews` database table.

### 6. Walk-In & Live Occupancy (Member 6)
*   **What Sarah does:** Suddenly, Mike walks into the restaurant on Friday night. Mike doesn't have an online account. Sarah quickly looks at her Admin iPad, sees an empty table on the Patio, sits Mike down, and presses "Lock Walk-In Table."
*   **Why it matters:** Member 6 writes the Front-of-House (FOH) logic that saves Mike's real-time table occupancy to the `walk_ins` database table. Thanks to Member 6, John (who is at home browsing the website) won't accidentally double-book Mike's table while he is eating!

---

### In Summary
**John makes an account** (Member 1). **Sarah creates her building layout** (Member 2) and **arranges her tables** (Member 3). **John books a table online** (Member 4), while **Sarah books a table offline for a random guest** (Member 6). Finally, **John leaves a glowing review** (Member 5). 

Everything perfectly connects together through the **SQLite Database**!
