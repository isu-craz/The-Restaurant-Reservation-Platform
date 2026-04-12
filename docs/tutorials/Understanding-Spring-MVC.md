# 🧠 Understanding How Spring Boot Connects HTML to Java

If you look at the `UserController.java` file and the `list-users.html` file, it looks incredibly overwhelming with words like `@GetMapping` and `@Autowired`. 

But don't panic! **Spring Boot is literally just a Restaurant.** 
If you understand how a restaurant works, you can easily explain your code to your team members!

---

## 🍽️ The Restaurant Analogy

### 1. The Ingredients (`User.java`)
Your Database Models (`User`, `Table`, `Review`) are just the raw food ingredients sitting in the back fridge (the SQLite database). You can't just hand raw ingredients to a customer on the web page.

### 2. The Kitchen Chef (`UserRepository.java`)
The Chef's only job is to open the fridge and grab ingredients. 
When your code says `userRepository.findAll()`, that is the Chef opening the SQLite fridge and grabbing every single `User` ingredient inside of it.

### 3. The Waiter (`UserController.java` as a `@RestController`)
The Waiter is the bridge between the Kitchen and the Customer! The Waiter never cooks the food (no database logic), and the Waiter never eats the food (no HTML logic). The Waiter just carries it.
*   **`@RestController`**: This tag tells Spring Boot, *"Hey, this Java class is a Waiter that serves raw Data!"*
*   **`@GetMapping("/api/users")`**: This is the Waiter figuring out which table to walk to! When the Javascript asks for `/api/users`, the Waiter walks over.

### 4. The Waiter's Delivery Box (JSON format)
When the Waiter (`UserController`) asks the Chef (`UserRepository`) for the food, the Waiter boxes it up! 
The Waiter packages the Java data into a universal format called **JSON**.
```java
// "Hey Chef, grab the users and hand them directly to whoever asked!"
return userRepository.findAll(); 
```

### 5. The Customer Unboxing It (`list-users.html`)
The Waiter drops the JSON box at the table (the HTML file). 
Now, your HTML file unboxes the data using a JavaScript `fetch()` command and puts it on the screen.

```html
<!-- "Hey Javascript, grab the box from the Waiter, unpack the JSON, and build the list!" -->
<script>
    fetch('/api/users')
        .then(res => res.json())
        .then(users => console.log(users));
</script>
```

---

## 🗣️ How to explain this to your team:

If your team asks you how `Component 1` works tomorrow, tell them to read this file. Then simply say:

> *"Guys, it's actually really simple. The `Repository` goes into SQLite and grabs the data. The `RestController` acts as a middle-man, converts that Java data into a JSON box, and hands it out. Then, our `HTML` files use Vanilla Javascript to fetch that JSON box and paint it on the screen! You literally just have to copy my `UserController` and change the word 'User' to 'Table'!"*
