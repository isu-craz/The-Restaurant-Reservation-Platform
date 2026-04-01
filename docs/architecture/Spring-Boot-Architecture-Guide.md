# 📂 Spring Boot Folder Structure & Architecture

A huge source of confusion for beginners is knowing **where** to put files in Spring Boot. Because Spring Boot is a massive framework, it forces you to use a very specific, organized folder structure.

If you put an HTML file in the wrong folder, the website will completely break!

Here is the exact map of where every single file in our `restaurant-platform` project belongs:

## The Golden Folder Map

```text
restaurant-platform/
│
├── src/
│   ├── main/
│   │   ├── java/com/se1020/restaurant/  <-- ☕ ALL JAVA CODE GOES HERE!
│   │   │   ├── models/                  (Your @Entity Database Classes)
│   │   │   ├── repositories/            (Your Database Interfaces)
│   │   │   ├── controllers/             (The "Traffic Cops" connecting Java to HTML)
│   │   │   └── RestaurantPlatformApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/                  <-- 🎨 ALL CSS, JS, & IMAGES GO HERE!
│   │       │   ├── css/style.css
│   │       │   └── images/logo.png
│   │       │
│   │       ├── templates/               <-- 🌐 ALL HTML FILES GO HERE!
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   └── dashboard.html
│   │       │
│   │       └── application.properties   <-- ⚙️ CONFIG (Database passwords, ports)
│
├── pom.xml                              <-- 📦 DEPENDENCIES (Where we put SQLite)
└── .gitignore
```

---

## How do Java and HTML talk to each other?

If you want to send a user's name from your Java database straight into your HTML screen, you use a **Controller**. Think of the Controller as the "Traffic Cop" sitting between Java and HTML.

### 1. The HTML (The View)
You put your HTML inside `src/main/resources/templates/home.html`. 
Because we installed **Thymeleaf**, you can write special `th:text` tags inside your normal HTML to automatically accept variables from Java:
```html
<!DOCTYPE html>
<html>
<body>
    <!-- The $ tells HTML to wait for Java to send a variable called "userName" -->
    <h1 th:text="${userName}">Default Name</h1>
</body>
</html>
```

### 2. The Java (The Controller)
You put your Java inside `src/main/java/com/se1020/restaurant/controllers/HomeController.java`.
The Java code intercepts the web browser, grabs data from the database, and injects it into the HTML dynamically:
```java
@Controller
public class HomeController {

    // When the user goes to "localhost:8080/home" in their browser...
    @GetMapping("/home")
    public String showHomePage(Model model) {
        
        // 1. Grab data (Imagine this came from your SQLite Database)
        String nameFromDatabase = "Admin Team Leader";
        
        // 2. Send the data to the HTML file under the variable name "userName"
        model.addAttribute("userName", nameFromDatabase);
        
        // 3. Tell Spring Boot to open "home.html" and stitch them together!
        return "home"; 
    }
}
```

That's the entire magic trick! 
- Java calculates the logic in `java/`
- HTML displays the logic in `resources/templates/`
- CSS makes the HTML look pretty in `resources/static/`
