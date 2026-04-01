# 🏗️ Project Initialization Guide (Group Leader)

If you are the Group Leader, you cannot just create an empty folder and upload it to GitHub. Your team needs a foundation! You must generate the "Spring Boot Project Skeleton" so that when your team downloads the code, they have the correct folder structure (`src/main/java`, `pom.xml`, etc.) to start writing their Java files.

Here is exactly how to generate that skeleton project. You only need to do this ONCE.

## Option 1: Using IntelliJ IDEA Ultimate (The Easiest Way)
If you have the student-free "Ultimate" version of IntelliJ, this is the most professional and fastest method:

1. Open IntelliJ -> **File -> New -> Project**
2. On the left menu, select **Spring Initializr** (or Spring Boot under Generators).
3. Fill out the project details:
   - **Name:** `restaurant-platform`
   - **Type:** Maven
   - **JDK:** Java 17 (or 21, just stay consistent!)
   - **Java:** 17
   - **Group:** `com.se1020`
   - **Artifact:** `restaurant-platform`
   - **Package name:** `com.se1020.restaurant`
4. Click **Next** and select these 3 dependencies:
   - `Spring Web`
   - `Thymeleaf`
   - `Spring Data JPA`
5. Click **Create**. IntelliJ will instantly build your entire folder structure for you.

## Option 2: Using Spring Initializr (The Manual Backup)
If you are using the Community Edition of IntelliJ or a different editor:

1. Go to **[https://start.spring.io/](https://start.spring.io/)**
2. Select: **Maven**, **Java 17**, and the 3 dependencies listed above.
3. Click **GENERATE**, download the `.zip`, and extract it into your project folder.

---

## 🚨 The Final Step: Injecting the SQLite Database!
Because the official Spring Boot generator doesn't have a simple checkbox for "SQLite", we have to add the database connection code manually. If you skip this step, the backend will completely crash!

1. Open the `pom.xml` file that was just created in your project folder.
2. Scroll down until you find the `<dependencies>` tag.
3. Paste this exact block of XML code anywhere *between* the `<dependencies>` and `</dependencies>` tags:

```xml
<!-- 1. The Core SQLite Database Driver -->
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.1.0</version>
</dependency>

<!-- 2. Required to make Spring Boot 3 talk to SQLite -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-community-dialects</artifactId>
</dependency>
```

4. Save the file. *(If IntelliJ pops up a little prompt asking to "Load Maven Changes", click it so it downloads the files!)*

5. **Finally, open `src/main/resources/application.properties` and paste these exact 5 lines at the very bottom of the document (underneath `spring.application.name`) so Spring Boot knows where to find the database:**

```properties
# Tell Spring Boot to create an SQLite file called 'restaurant.sqlite'
spring.datasource.url=jdbc:sqlite:restaurant.sqlite
spring.datasource.driver-class-name=org.sqlite.JDBC

# Tell Spring Boot how to auto-generate our Database Tables
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🌟 The True Final Step: Injecting the Master Template
If you upload this folder to GitHub right now, your team will download an empty shell. They won't have a template to copy, and they will be confused! 

**As the Team Leader, your final job before uploading to GitHub is to build Component 1 (User Management).** 
Build the User Model, the User Controller, and the 3 HTML User pages directly into this project. *(Need the exact code? See [Master-Template-Code](Master-Template-Code.md))*
Test it by running the code and visiting `http://localhost:8080/users`.

Once you have verified that Component 1 works perfectly, it officially becomes the **Golden Template** for your entire team to copy tomorrow.

---

### What to do next?
Now you are officially ready to upload everything (the skeleton + your Golden Template) to GitHub! Go back to the **Project Roadmap** inside [`docs/project-roadmap.md`](../project-roadmap.md) and move on to the next step (uploading to GitHub).
