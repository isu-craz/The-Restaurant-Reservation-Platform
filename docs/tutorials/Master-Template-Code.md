# 👑 The Golden Template Code (Component 1)

**For the Team Leader Only:** Because you are initializing the project, you have to build the very first Component yourself so your team has a perfect working example to copy.

If you ever lose the Component 1 files or need to rebuild the project from scratch, **just copy and paste the code blocks below into their exact file locations.**

---

## 1. The Java Models

Create these files inside: `src/main/java/com/se1020/restaurant/models/`

### `User.java` (The Core Model)
```java
package com.se1020.restaurant.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String password;
    private String role; // Stores "CUSTOMER" or "ADMIN"

    public User() {} 

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
```

---

## 2. The Repository

Create this file inside: `src/main/java/com/se1020/restaurant/repositories/`

### `UserRepository.java`
```java
package com.se1020.restaurant.repositories;

import com.se1020.restaurant.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Spring Boot writes all the SQL automatically!
}
```

---

## 3. The Controller

Create this file inside: `src/main/java/com/se1020/restaurant/controllers/`

### `UserController.java`
```java
package com.se1020.restaurant.controllers;

import com.se1020.restaurant.models.User;
import com.se1020.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public User saveUser(@RequestBody User user) {
        user.setRole("CUSTOMER");
        return userRepository.save(user); 
    }

    @PutMapping("/edit/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
```

---

## 4. The HTML Templates

Create these three files inside: `src/main/resources/static/users/` *(you must create the `users` folder inside static!)*

### `list-users.html`
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management Dashboard</title>
    <!-- Use Tailwind CSS for rapid styling -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 p-10 font-sans text-slate-900">
    <div class="max-w-5xl mx-auto">
        <div class="flex justify-between tracking-tight items-center mb-8">
            <h1 class="text-3xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-indigo-600 to-purple-600">User Management</h1>
            <a href="/users/add" class="bg-indigo-600 px-5 py-2.5 text-white font-semibold rounded-lg hover:-translate-y-0.5 transition shadow-indigo-600/20 shadow-lg">+ Add New User</a>
        </div>

        <div class="bg-white rounded-2xl shadow-xl shadow-slate-200/50 border border-slate-100 p-8 overflow-hidden">
            <table class="w-full text-left">
                <thead>
                    <tr class="text-sm uppercase tracking-wider text-slate-500 border-b-2 border-slate-100">
                        <th class="pb-4">ID</th>
                        <th class="pb-4">Type</th>
                        <th class="pb-4">Username</th>
                        <th class="pb-4">Email</th>
                        <th class="pb-4">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr th:each="user : ${users}" class="border-b border-slate-50 hover:bg-slate-50 transition">
                        <td class="py-4 font-semibold text-slate-700" th:text="${user.id}">1</td>
                        <td class="py-4"><span class="bg-indigo-50 text-indigo-600 text-xs font-bold px-3 py-1 rounded-full uppercase" th:text="${user.class.simpleName}">Role</span></td>
                        <td class="py-4 font-medium" th:text="${user.username}">Name</td>
                        <td class="py-4 text-slate-500" th:text="${user.email}">Email</td>
                        <td class="py-4 flex gap-2">
                            <a th:href="@{/users/edit/{id}(id=${user.id})}" class="bg-slate-100 text-slate-700 px-3 py-1.5 rounded-md text-sm font-semibold hover:bg-slate-200">Edit</a>
                            <a th:href="@{/users/delete/{id}(id=${user.id})}" class="bg-red-50 text-red-600 px-3 py-1.5 rounded-md text-sm font-semibold hover:bg-red-100" onclick="return confirm('Delete this user?');">Delete</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
```

### `add-user.html`
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New User</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 p-10 font-sans text-slate-900 flex justify-center mt-10">
    <div class="w-full max-w-lg bg-white rounded-2xl shadow-xl shadow-slate-200/50 border border-slate-100 p-8">
        <h1 class="text-2xl font-bold mb-2">Create New Account</h1>
        <p class="text-slate-500 mb-8">Register a powerful new user to the database.</p>

        <form th:action="@{/users/add}" th:object="${user}" method="post" class="space-y-5">
            <div>
                <label class="block text-sm font-semibold mb-1">Username</label>
                <input type="text" th:field="*{username}" class="w-full border border-slate-200 bg-slate-50 p-3 rounded-lg outline-none focus:border-indigo-600 focus:bg-white transition" required>
            </div>
            <div>
                <label class="block text-sm font-semibold mb-1">Email</label>
                <input type="email" th:field="*{email}" class="w-full border border-slate-200 bg-slate-50 p-3 rounded-lg outline-none focus:border-indigo-600 focus:bg-white transition" required>
            </div>
            <div>
                <label class="block text-sm font-semibold mb-1">Password</label>
                <input type="password" th:field="*{password}" class="w-full border border-slate-200 bg-slate-50 p-3 rounded-lg outline-none focus:border-indigo-600 focus:bg-white transition" required>
            </div>
            <button type="submit" class="w-full bg-indigo-600 text-white font-semibold rounded-lg p-3 hover:bg-indigo-700 transition">💾 Save User</button>
        </form>
        <div class="mt-6 text-center"><a href="/users" class="text-slate-500 hover:text-indigo-600 text-sm font-medium">← Cancel</a></div>
    </div>
</body>
</html>
```

### `edit-user.html`
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 p-10 font-sans text-slate-900 flex justify-center mt-10">
    <div class="w-full max-w-lg bg-white rounded-2xl shadow-xl shadow-slate-200/50 border border-slate-100 p-8">
        <h1 class="text-2xl font-bold mb-2">Edit User Profile</h1>
        <p class="text-slate-500 mb-8">Permanently update details.</p>

        <form th:action="@{/users/edit/{id}(id=${user.id})}" th:object="${user}" method="post" class="space-y-5">
            <div>
                <label class="block text-sm font-semibold mb-1">Username</label>
                <input type="text" th:field="*{username}" class="w-full border border-slate-200 bg-slate-50 p-3 rounded-lg outline-none focus:border-slate-800 focus:bg-white transition" required>
            </div>
            <div>
                <label class="block text-sm font-semibold mb-1">Email</label>
                <input type="email" th:field="*{email}" class="w-full border border-slate-200 bg-slate-50 p-3 rounded-lg outline-none focus:border-slate-800 focus:bg-white transition" required>
            </div>
            <button type="submit" class="w-full bg-slate-900 text-white font-semibold rounded-lg p-3 hover:-translate-y-0.5 shadow-lg transition">💾 Save Changes</button>
        </form>
        <div class="mt-6 text-center"><a href="/users" class="text-slate-500 hover:text-slate-900 text-sm font-medium">← Cancel Update</a></div>
    </div>
</body>
</html>
```
