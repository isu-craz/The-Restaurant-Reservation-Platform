# 🎯 SE1020 Project Strategy & Development Guide

Since this is our first large-scale group project, we need a clear strategy on how we are going to build this **Restaurant Table Reservation System** together successfully. Here is the approach we are taking based on our university rubric.

## 1. Our Core Focus (Java OOP, NOT Web Design)
The main focus of this project is demonstrating backend logic using Java Object-Oriented Programming. **This is not a web-design class.** A review of the course lectures (`lecs` folder) shows the curriculum covers Abstraction, Class Diagrams, Exception Handling, and SOLID Principles. 

Therefore, we will focus our efforts purely on **CRUD Database Operations** and Java OOP principles, rather than attempting to build a complex, real-world web application. Building a flashy, over-complicated frontend is not the goal; building a solid, object-oriented Java backend is what will earn top marks.

## 2. Our Technology Stack Strategy
When deciding what tools to use for this project, we needed a balance between fulfilling the SE1020 grading requirements and keeping things simple enough for our first project. Here is why we made these choices:

*   **Java & Spring Boot:** The project documentation clearly stated we must develop a web application using Java technologies. While we could have built it using raw JSP and Servlets, using **Spring Boot** is a much better choice. It is the modern industry standard, and it automatically handles a lot of the complicated web setup, allowing us to just focus on writing our core OOP logic.
*   **Data Storage (SQLite Database):** Since it's our first project, trying to set up and connect a complex external SQL server like MySQL would be very hard. However, we are allowed to use a Database connection. Therefore, saving our data to a simple local `sqlite.db` file using Spring Data JPA is an incredibly easy choice that perfectly fits the requirements and saves us from writing hundreds of lines of manual file reading code.
*   **HTML, CSS & Thymeleaf:** For the front end, we are keeping it straightforward. We will use templates (like Tailwind or Bootstrap) for a clean User Interface. To connect our Java data to the HTML, we are using a tool called **Thymeleaf**, which is built right into Spring Boot.

## 2. Workload Distribution & Project Scope
We have a team of 6 members. We cannot have one person do "all the HTML" and another do "all the Java." That will cause massive delays and unfair grading.

Because the rubric requires a **minimum of 3 CRUD operations per member**, our safest strategy is to split the work by **Features** (vertically) across our 6 core components:

<!-- ====================================================================== -->
*   **Member 1:** User Management & Auth
*   **Member 2:** Dining Area Management
*   **Member 3:** Table Management
*   **Member 4:** Reservation Management
*   **Member 5:** Review & Complaint Management
*   **Member 6:** Walk-In & Live Occupancy Management
<!-- those are just examples of what we can do, we can change them if needed -->

Every team member is responsible for wiring up a basic HTML template, their Java Controller, and the SQLite Database Entity logic *just* for their specific component. **Do not worry about complex UI design.** We are just copying simple, pre-made forms to capture data. This ensures everyone gets equal practice applying the core Java OOP concepts that we are actually graded on.

## 3. Applying Object-Oriented Programming (OOP)
A major part of our marks will come from how well we use OOP. When building your specific component, make sure you use:
*   **Encapsulation:** Keep your variables `private` in your models (e.g., `Reservation`, `Restaurant`) and use `getters` and `setters`.
*   **Inheritance:** We created parent classes (like `Reservation`) that specific child classes (like `VIPReservation` or `StandardReservation`) extend.
*   **Polymorphism:** Use method overriding (e.g., different table types overriding how they `calculateBookingFee()`).

## 5. Communication & Tracking Progress
*   Use a dedicated WhatsApp group for quick questions.
*   Don't work in isolation! If your code isn't reading the database properly, ask the team. We are all learning together and using the same standard template.
*   **Do not waste time on CSS!** Focus heavily on making sure your Java **Create, Read, Update, Delete (CRUD)** operations work. A basic HTML form that correctly saves data to the SQLite database will get a perfect grade, while a beautiful, animated website that crashes will fail the rubric.

## 6. The "Prototype" Development Plan
To ensure no one gets stuck or overwhelmed, we will follow this step-by-step approach:

*   **Step 1: Initialization:** The team leader will generate a blank Spring Boot "Skeleton" project containing the necessary folder structures and push it to the GitHub repository.
*   **Step 2: Environment Setup:** Everyone clones the project to their own laptops to test it locally on `localhost:8080`.
*   **Step 3: Prototyping ONE Feature Together:** The team will collaborate to build just **Component 1 (User Profiles)** together from start to finish. We will create the simple HTML form, implement the Java logic to write to the SQLite Database, and ensure it works end-to-end.
*   **Step 4: Adapting the Reference Template:** Once that first feature is working, the remaining 5 members will have a perfectly working **reference template**. If you are assigned "Table Management", you can simply review the Java code used for "User Profiles" and adapt the logic to handle Tables instead. The underlying mechanics are exactly the same across all 6 components.
