# 🚀 How to Run the Project Locally

This guide explains how to start and stop the project on your own computer, and what to do when something goes wrong.

---

## Step 1: Starting the Project

1. Open IntelliJ IDEA and make sure your project is open (you should see the folder tree on the left side).
2. Find the file `src/main/java/com/se1020/restaurant/RestaurantPlatformApplication.java` in the folder tree.
3. Click the big **green Play button (▶)** at the top right of the IntelliJ window. 
4. Watch the output panel at the bottom of your screen. Wait until you see this exact line:
```
Started RestaurantPlatformApplication in X.XXX seconds
```
5. That's it! Your project is now running!

---

## Step 2: Viewing Your Website

Spring Boot does NOT automatically open your web browser for you. You have to do it yourself!

1. Open **Google Chrome** or **Safari**.
2. Click on the address bar at the top and type exactly:
```
http://localhost:8080
```
3. Press Enter.

**What does `localhost:8080` mean?**
- `localhost` = "My own computer". It is NOT on the internet. Only you can see it.
- `8080` = The door number your Spring Boot server listens to.
- To visit a specific page (like your User Management dashboard), type: `http://localhost:8080/users`

---

## Step 3: Stopping the Project

1. Look at the bottom of your IntelliJ IDEA window.
2. Click the **red Stop button (⏹)**.
3. The server will shut down instantly.

> ⚠️ **Important:** Whenever you make changes to your Java code, you MUST stop and restart the project for the changes to take effect! HTML changes sometimes reload automatically, but Java changes never do.

---

## Common Errors & Fixes

### ❌ "APPLICATION FAILED TO START" + "Failed to configure a DataSource"
**What happened:** Spring Boot is looking for your database connection settings and can't find them.

**Fix:** Open `src/main/resources/application.properties` and make sure these lines are at the bottom:
```properties
spring.datasource.url=jdbc:sqlite:restaurant.sqlite
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### ❌ "Whitelabel Error Page" in your browser
**What happened:** Spring Boot is running perfectly! This error just means you visited a URL that has no HTML page assigned to it yet.

**Fix:** Make sure you are visiting the correct URL. If you built the Users component, go to `http://localhost:8080/users`, not just `http://localhost:8080`.

### ❌ Port 8080 is already in use
**What happened:** You already ran the project once and forgot to stop it, so the old server is still running and blocking the new one.

**Fix:** Go to IntelliJ, look at the bottom panel, and click the red Stop button on the old session. Then hit Play again.
