# 🧠 From University Console to Spring Boot (For Beginners)

If you are reading this, you are probably used to writing Java code that looks like this:
```java
// What you are used to doing in University:
public static void main(String[] args) {
    Membership basicUser = new Membership("ID001", "Nimal", 12);
    System.out.println("User is: " + basicUser.memberName);
}
```

When you look at Spring Boot, it looks terrifying because there is NO `public static void main` doing the work, and nobody is writing `System.out.println()`!

Here is the secret: **Spring Boot is exactly the same as your University code.** It just uses different words. Let's map your exact university knowledge directly to our Restaurant platform!

---

## 1. The Class Variables (`@Entity`)

In your university code, you wrote a `Membership` class to hold your data:
```java
// YOUR UNIVERSITY CODE
public class Membership {
    protected String membershipID;
    protected String memberName;
}
```

In Spring Boot, we do the EXACT same thing, but we add the `@Entity` sticky note to the top. This sticky note tells Spring Boot: *"Hey, instead of saving this data in Ram memory, automatically save it permanently into an SQLite database file!"*
```java
// SPRING BOOT CODE
@Entity 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   // <--- This is your `membershipID`!
    
    private String username; // <--- This is your `memberName`!
}
```
* **What is `@Id` and `@GeneratedValue`?** In your university code, you had to manually type `"ID001"` when you created Nimal. In Spring Boot, the `@GeneratedValue` tag tells the database: *"Don't make me type the ID. You do the math for me."*

## 2. The Constructors and Setup
In your university code, you spent a lot of time writing parameterized constructors to fill your objects with data.
```java
// YOUR UNIVERSITY CODE
public Membership(String membershipID, String memberName) {
    this.membershipID = membershipID;
    this.memberName = memberName;
}
```

In Spring Boot Database Models, **you ONLY need one completely empty constructor**. 
Why? Because when Spring Boot pulls Nimal out of the SQLite database, it needs a "blank canvas" first. It creates `new User()`, and then it uses **Setters** (`setUsername("Nimal")`) to fill the data in for you!
```java
// SPRING BOOT CODE
public User() {} // You MUST have this blank constructor!
```

## 3. Where is `System.out.println()`? (The Waiter)
In your university code, you used `displayMembershipDetails()` and `System.out.println()` to visually show the user on your console screen.
```java
// YOUR UNIVERSITY CODE
public void displayMembershipDetails() {
    System.out.println("Member Name: " + this.memberName);
}
```

In Spring Boot, we are building a website, not a black terminal window! We use **HTML code** instead of `System.out.println()`.
But Java can't talk directly to HTML. It needs a Waiter! That Waiter is called a `@Controller`.

```java
// SPRING BOOT CODE
@Controller
public class UserController {

    @GetMapping("/users")
    public String displayMembershipDetails(Model model) {
        
        // 1. Grab Nimal from the Database
        List<User> allUsers = database.findAll();
        
        // 2. Put Nimal onto the "model" serving tray
        model.addAttribute("users", allUsers);
        
        // 3. Walk over to "list-users.html" and drop the tray!
        return "users/list-users";
    }
}
```

## Summary for your Brain
1. Your `Membership.java` holding variables = Our `@Entity`.
2. Instead of typing `new Membership("Nimal")`, the user types "Nimal" into an HTML text-box on the website.
3. Instead of printing `System.out.println(Nimal)`, Spring Boot puts Nimal onto a `Model` serving tray and gives him to the HTML file!
4. **Where is `main()`?** Spring Boot runs it in the background for you. You never have to write it!
