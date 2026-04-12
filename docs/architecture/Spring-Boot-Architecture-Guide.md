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
│   │       ├── static/                  <-- 🌐💻 ALL HTML, CSS, & JS FILES GO HERE!
│   │       │   ├── css/style.css
│   │       │   ├── js/app.js
│   │       │   ├── images/logo.png
│   │       │   ├── index.html
│   │       │   └── dashboard.html
│   │       └── application.properties   <-- ⚙️ CONFIG (Database passwords, ports)
│
├── pom.xml                              <-- 📦 DEPENDENCIES (Where we put SQLite)
└── .gitignore
```

---

## How do Java and HTML talk to each other?

If you want to send a user's name from your Java database straight into your HTML screen, you use a **Controller**. Think of the Controller as the "Traffic Cop" sitting between Java and HTML.

### 1. The HTML (The View)
You put your HTML inside `src/main/resources/static/home.html`. 
Because we use **Vanilla JavaScript**, you write a standard HTML file that fetches data from the backend:
```html
<!DOCTYPE html>
<html>
<body>
    <!-- JS will inject the data here later -->
    <h1 id="userNameDisplay">Loading...</h1>
    <script>
        fetch('/api/home/user')
            .then(r => r.json())
            .then(data => document.getElementById('userNameDisplay').innerText = data.name);
    </script>
</body>
</html>
```

### 2. The Java (The REST Controller)
You put your Java inside `src/main/java/com/se1020/restaurant/controllers/HomeController.java`.
The Java code intercepts the web API request and returns raw JSON data:
```java
@RestController
public class HomeController {

    // When the JS fetches "localhost:8080/api/home/user"...
    @GetMapping("/api/home/user")
    public String showHomePage() {
        
        // 1. Grab data (Imagine this came from your SQLite Database)
        String nameFromDatabase = "Admin Team Leader";
        
        // 2. Return the data as JSON
        return "{\"name\": \"" + nameFromDatabase + "\"}"; 
    }
}
```

That's the entire magic trick! 
- Java calculates the logic in `java/` and serves JSON APIs.
- HTML and JS displays the UI and fetches the data in `resources/static/`
