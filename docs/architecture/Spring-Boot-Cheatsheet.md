# 🛡️ Spring Boot Beginner's Cheatsheet

Spring Boot will feel like "magic" at first. It does a lot of things automatically in the background, but if you break its rules, it throws terrifying errors.

Here are the 4 most common things beginners absolutely need to know, and the mistakes that will cost you hours of debugging if you don't understand them!

## 1. The Magic "At" Symbols (Annotations)
You will see symbols like `@Controller` or `@Entity` everywhere in Spring Boot. These are called **Annotations**. They are basically sticky notes you put on your code to tell Spring what to do in the background.

- `@Entity`: Put this on top of a Java class to say, *"Hey Spring, turn this Java class into an actual Table in the SQLite database!"*
- `@Id`: Put this on top of an `Integer id` variable to say, *"Hey Spring, this variable is the Primary Key."*
- `@Controller`: Put this on top of a class to say, *"Hey Spring, this class is the Traffic Cop that talks to the HTML web pages."*
- `@GetMapping("/login")`: *"Hey Spring, when the user types `website.com/login` into the browser, run this exact method!"*

## 2. The Golden Rule of `new` (Dependency Injection)
In basic Java, if you wanted to use a database, you would write:
`UserRepository db = new UserRepository();`

**🚨 DO NOT DO THIS IN SPRING BOOT! 🚨**
If you manually construct an object using `new`, Spring Boot gets angry because *you* built it, not Spring. Spring will refuse to connect your manually-built object to the SQLite database, and you will get a massive `NullPointerException` when you try to save data.

**The Fix:** You must ask Spring to give you the database connection automatically using the `@Autowired` annotation.

```java
@Controller
public class UserController {
    
    // Spring built the database connection for you automatically! 
    // Just stick @Autowired on top of it, and Spring injects it perfectly.
    @Autowired
    private UserRepository userRepository; 
    
}
```

## 3. The "Port 8080 Already in Use" Error
This is the #1 error you will see as a beginner! It happens when you hit the green "Play" button in IntelliJ, then you edit some code, and you hit "Play" again.
Spring Boot tries to start a second web server, but your first server is secretly still running on Port 8080 in the background!

**The Fix:** You must always hit the solid **Red Square Stop Button** in IntelliJ IDEA to kill the first server before you hit the green Play button again.

## 4. The HTML Variable Trap (`fetch().then()`)
When your Java `@RestController` sends variables (like a User's Name) as JSON to your HTML file, you cannot just print the variable normally inside HTML tags. You must use JavaScript `fetch` to ask for it, and then modify the DOM.

**❌ Wrong (Standard HTML Expecting Magic):**
`<p>Welcome, {userName}!</p>`

**✅ Right (Vanilla JS API Fetch):**
```html
<p id="welcomeText">Loading...</p>
<script>
    fetch('/api/user/info')
        .then(res => res.json())
        .then(data => document.getElementById('welcomeText').innerText = "Welcome, " + data.userName + "!");
</script>
```

*(JavaScript fetches the JSON payload from your Java REST Controller and injects it).*

---
Keep this cheatsheet open when you start typing your first Java components. It will save you and your team hours of confusion!
