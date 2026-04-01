# 🔗 The Master Strategy & Integration Guide

This document is the **single source of truth** for our Multi-Restaurant Platform project. If anyone on the team is confused about what to do next or how their code connects to someone else's code, read this guide.

> [!IMPORTANT]
> The biggest trap in group projects is everyone trying to build their UI and logic at the same time in isolation. If you do this, your code will never connect properly. To succeed, we must build the **Database Foundation** together first.

Here is our exact, step-by-step roadmap from day one to the final submission.

---

## Step 0: The Skeleton Setup (The First Day)
Before anyone writes a single line of code, the Team Leader creates the foundation.

1. **Spring Initializr:** The Team Leader generates a blank Spring Boot project using [start.spring.io](https://start.spring.io).
2. **The Golden Dependencies:** They add `Spring Web`, `Thymeleaf`, and crucially, `Spring Data JPA`.
3. **The Database:** They manually add the `sqlite-jdbc` dependency to the `pom.xml` file.
4. **GitHub:** The Team Leader uploads this blank, perfectly working "skeleton" to GitHub. Now, all 6 members clone this exact same starting point to their laptops.

> [!TIP]
> From this point forward, **nobody ever edits the `main` branch on GitHub.** Every team member creates their own branch (e.g., `feature/table-module`) so we don't accidentally delete each other's code!

---

## Step 1: The "Database Shell" Week (Do this together!)
How does Member 4 (Reservations) book a Table if Member 3 hasn't finished writing the Table code yet?
**Answer:** Because your Java code *does not* talk to their Java code. **Everybody's code talks to the SQLite Database.**

But wait, we don't have a database yet! That's why Week 1 is dedicated to building it.

Get on a group video call. The Team Leader shares their screen, and together you write the **Entity Models**. In Spring Boot, when you write a Java Class with `@Entity`, Spring automatically generates the SQLite Database table for you.

You will build these 6 files together:
1.  `User.java` (Just one smart file utilizing a "Role" string)
2.  `DiningArea.java`
3.  `Table.java`
4.  `Reservation.java`
5.  `Review.java`
6.  `WalkIn.java`

> [!NOTE]
> Why do this together? Because now, the SQLite Database officially exists. It has columns for User ID, Table Capacity, etc. The rules of your universe are defined, and everyone agrees on the exact spelling of the data!

---

## Step 2: The Linking Phase (Foreign Keys)
During that same group call, you must connect the database tables together using Java annotations. This is how the modules officially "integrate."

For example, in `Reservation.java`, Member 4 will write:
```java
@ManyToOne
private User customer;

@ManyToOne
private Table bookedTable;
```
Now, Spring Boot knows that a Reservation *requires* a User and a Table! The database completely understands how the pieces fit together.

---

## Step 3: Generating Data Fetchers (Repositories)
The last thing the team does together is generate the tools used to pull data out of those SQLite tables. In Spring Data JPA, these are called Repositories.

1. `UserRepository.java` (extends JpaRepository)
2. `DiningAreaRepository.java`
3. `TableRepository.java`
4. `ReservationRepository.java`
5. `ReviewRepository.java`
6. `WalkInRepository.java`

---

## Step 4: Independent Feature Building (Go to your corners!)
The Database is built. The Repositories exist. Now, the team can separate. Everyone goes to their own computer, switches to their own Git branch, and starts building their HTML, Controllers, and business logic. 

**Here is the magic of Integration in action:**

Let's say Member 4 is writing the "Book a Table" Java Controller. They need a `Table` object, but Member 3 hasn't finished the "Create a Table" HTML page yet! 

**It doesn't matter!** Member 4 can just ask the `TableRepository` to fetch a table from the database:

```java
// Inside Member 4's ReservationController.java

// Inject the tools to fetch data from the other modules!
@Autowired
private TableRepository tableRepository;

@Autowired
private UserRepository userRepository;

public String createBooking(Long tableId, Long userId, String date) {
    // ⬇️ Member 4 is grabbing data from Member 3's and Member 1's tables WITHOUT needing their code!
    Table targetTable = tableRepository.findById(tableId).get();
    User targetUser = userRepository.findById(userId).get();
    
    // Now Member 4 creates their own Reservation object and saves it
    Reservation newBooking = new Reservation(targetUser, targetTable, date);
    reservationRepository.save(newBooking);
    
    return "Success!";
}
```

> [!CAUTION]
> If Member 3 hasn't built the UI to create tables yet, how does Member 4 test their reservation code? 
> **Easy:** Member 4 can just open SQLite Studio on their laptop, manually type a fake "Window Seat Table" into the database row, and test their code against it! **No one is ever blocked waiting for someone else to finish.**

---

## Step 5: The "Guinea Pig" Prototype (Optional but Highly Recommended)
Before everyone starts writing their specific HTML and Controllers in Step 4, it is highly recommended that the team stays on the group call and builds **one single module** (like User Registration) from start to finish together.

This creates a working, bug-free **Reference Template**. Then, when the team separates, they can literally copy-paste the User Controller code and just change the word "User" to "Table" to ensure nobody gets stuck on syntax errors.
