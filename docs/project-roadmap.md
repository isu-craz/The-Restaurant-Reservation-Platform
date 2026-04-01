# 🗺️ Project Roadmap & Checklist

Welcome! This is our master checklist for the entire project. Follow these steps in order. If you ever get stuck or don't know how to do a specific step, **click the link next to it for a detailed tutorial**.

---

## Phase 1: Initial Setup (Group Leader Only)
*These steps only need to be done once by the leader.*

- [ ] **Step 1:** Download and install Java 17 on your computer. *(Need help? See [Java-Installation-Guide](tutorials/Java-Installation-Guide.md))*
- [ ] **Step 2:** Install Git on your computer. *(Need help? See [Git-Guide-Leader](tutorials/Git-Guide-Leader.md))*
- [ ] **Step 3:** Generate the Spring Boot Project Skeleton! *(Need help? See [Project-Initialization-Guide](tutorials/Project-Initialization-Guide.md))*
- [ ] **Step 4:** Create a new repository on GitHub.com.
- [ ] **Step 5:** Upload this entire folder (the tutorials + the Spring Boot skeleton) to your new GitHub repository.
- [ ] **Step 6:** Invite all team members to the GitHub repository as collaborators.
- [ ] **Step 7:** Download and install [SQLiteStudio](https://sqlitestudio.pl/) so you can visually inspect the database while you code. *(Need help? See [Database-Viewing-Guide](tutorials/Database-Viewing-Guide.md))*

---

## Phase 2: Team Member Setup (Everyone)
*Every team member must complete these steps before writing code.*

- [ ] **Step 1:** Download and install Java 17 on your computer. *(Need help? See [Java-Installation-Guide](tutorials/Java-Installation-Guide.md))*
- [ ] **Step 2:** Install Git on your computer. *(Need help? See [Git-Guide-Member](tutorials/Git-Guide-Member.md))*
- [ ] **Step 3:** Check your email and accept the GitHub repository invitation.
- [ ] **Step 4:** Open IntelliJ IDEA and **Clone the Repository** to your computer. *(Need help? See [Git-Guide-Member](tutorials/Git-Guide-Member.md))*
- [ ] **Step 5:** Once downloaded, check for any extra documents or instructions inside the project folder (like the ones in `tutorials/` or `architecture/`) and read them!
- [ ] **Step 6:** Download and install [SQLiteStudio](https://sqlitestudio.pl/) so you can open the `.sqlite` file and watch your data save in real time. *(Need help? See [Database-Viewing-Guide](tutorials/Database-Viewing-Guide.md))*

---

## Phase 3: Planning & Work Distribution (Team Meeting)
*Have a quick group call to decide this together.*

- [ ] **Step 1: 🛑 MANDATORY DEVELOPER READING** 
  Before anyone touches IntelliJ, the entire team MUST read these short guides to understand how Spring Boot actually works:
  - 📖 **[Project Strategy & Folder Architecture](architecture/Project-Strategy-Guide.md)** (Why we chose SQLite and where files go)
  - 🧠 **[Understanding Java Models & @Entity](tutorials/Understanding-Java-Models.md)** (How to read Database code)
  - 🛡️ **[Spring Boot Beginner's Cheatsheet](architecture/Spring-Boot-Cheatsheet.md)** (How to avoid the 4 fatal student errors)
  - 🚀 **[How to Run the Project Locally](tutorials/Running-The-Project.md)** (How to start the server, view localhost, and fix common crashes)
  - 🔧 **[Building Your Component (Copy-Paste Template)](tutorials/Building-Your-Component.md)** (The complete Model + Controller + HTML template — just change the names!)
- [ ] **Step 2:** Assign the 6 Core Components of the Restaurant Platform to specific team members. **For each component, the assignee MUST build a minimum of 3 UI HTML pages and at least 3 CRUD operations (like Create/Read/Update/Delete) to satisfy the university rubric!**
  - 👤 Member 1: **User Management & Auth** (HTML, `User` Entity, `users` Database Table)
    - *Fields:* ID (Primary Key), Username (String), Email (String), Password (String), Role (String: "CUSTOMER" or "ADMIN").
  - 👤 Member 2: **Dining Area Management** (HTML, `DiningArea` Entity, `dining_areas` Database Table)
    - *Fields:* ID (Primary Key), Name (String), ExtraBookingFee (Double), IsSmokingAllowed (Boolean).
  - 👤 Member 3: **Table Management** (HTML, `Table` Entity, `tables` Database Table)
    - *Fields:* ID (Primary Key), TableNumber (Integer), SeatingCapacity (Integer), DiningArea (Foreign Key `@ManyToOne` linking to DiningArea).
  - 👤 Member 4: **Reservation Management** (HTML, `Reservation` Entity, `reservations` Database Table)
    - *Fields:* ID (Primary Key), ReservationDate (String), ReservationTime (String), GuestCount (Integer), User (Foreign Key), Table (Foreign Key).
  - 👤 Member 5: **Review & Complaint Management** (HTML, `Review` Entity, `reviews` Database Table)
    - *Fields:* ID (Primary Key), Rating (Integer 1-5), Comment (String), ReviewDate (String), User (Foreign Key `@ManyToOne`).
  - 👤 Member 6: **Walk-In Management** (HTML, `WalkIn` Entity, `walk_ins` Database Table)
    - *Fields:* ID (Primary Key), GuestName (String), WalkInTime (String), GuestCount (Integer), Table (Foreign Key `@ManyToOne`).

---

## Phase 4: The Daily Coding Loop (Everyone)
*Follow these exact steps EVERY TIME you sit down to write code.*

- [ ] **Step 1:** Get the newest updates: `git pull origin main`
- [ ] **Step 2:** Create your own safe workspace: `git checkout -b your-name/your-feature`
- [ ] **Step 3:** Write your Java/HTML code and test it locally!
- [ ] **Step 4:** Save your work: `git add .` and `git commit -m "Message"`
- [ ] **Step 5:** Upload your work: `git push origin your-name/your-feature`
- [ ] **Step 6:** Go to GitHub and open a **Pull Request** so the leader can safely merge your code.

*(Need a refresher on these commands? See [Git-Guide-Member](tutorials/Git-Guide-Member.md))*

---

## Phase 5: Final Deliverables & Viva Prep (End of Project)
*Do not start this phase until Phase 4 is 100% complete for all members.*

- [ ] **Step 1:** Generate the Class Diagrams from our Java codebase.
- [ ] **Step 2:** Export our GitHub Repository commit history log to prove our teamwork.
- [ ] **Step 3:** Compile the Final Report containing the diagrams and logs.
- [ ] **Step 4:** Prepare sample `.sqlite` test data for the viva demonstration.
- [ ] **Step 5:** Final Team Practice Run to ensure everyone can eloquently explain how they used **Encapsulation**, **Inheritance**, and **Polymorphism** in their code.
