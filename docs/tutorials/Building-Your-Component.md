# 🔁 How to Build Your Component (Copy-Paste Template Guide)

This is the most important guide in the entire project. Read this before you write a single line of code.

**Before you start — read this first.**

If you are new to team projects, you might feel like using someone else's code as a starting point is "cheating" or "lazy". It is actually the opposite.

In real software companies like Google, Facebook, and Amazon, senior engineers deliberately build a **"template component"** first. Then every other developer on the team copies that template and adapts it for their own feature. This practice has an official name in the industry: it is called building a **"Reference Implementation"** or a **"Golden Template"**.

Why do professional teams do this?
- ✅ It keeps the entire codebase consistent (all files look the same, easy to read)
- ✅ It avoids mistakes (if the template is correct, all copies start correct)
- ✅ It is dramatically faster (you spend time on logic, not boilerplate setup)

**The skill is not in typing code from zero. The skill is in understanding what to change and why.**

When you copy `UserController.java` and adapt it into `RestaurantController.java`, you are doing exactly what a junior developer at a real company does on their first week. You are reading existing code, understanding its structure, and modifying it to solve a new problem. That is genuine software engineering.



The Team Leader has already built **Component 1 (User Management)** as the master template. You can find the existing files right now inside the project at these exact locations:

| Component 1 File to Copy | Find it here |
|---|---|
| `User.java` | `src/main/java/com/se1020/restaurant/models/User.java` |
| `UserRepository.java` | `src/main/java/com/se1020/restaurant/repositories/UserRepository.java` |
| `UserController.java` | `src/main/java/com/se1020/restaurant/controllers/UserController.java` |
| `list-users.html` | `src/main/resources/templates/users/list-users.html` |
| `add-user.html` | `src/main/resources/templates/users/add-user.html` |
| `edit-user.html` | `src/main/resources/templates/users/edit-user.html` |

Open any of those files in IntelliJ, copy the entire content, paste it into a new file with your component's name, and then replace every occurrence of `User`/`user`/`users` with your own component name. The templates in this guide below will also walk you through which lines to change.

---

## What You Are Building

Every team member must build exactly **4 files** for their component:

| File | What it is | Where it goes |
|---|---|---|
| `YourModel.java` | The Database blueprint | `src/main/java/com/se1020/restaurant/models/` |
| `YourRepository.java` | The database connector | `src/main/java/com/se1020/restaurant/repositories/` |
| `YourController.java` | The logic + routing | `src/main/java/com/se1020/restaurant/controllers/` |
| `list-yours.html` | The View Dashboard | `src/main/resources/templates/yours/` |
| `add-yours.html` | The Create Form | `src/main/resources/templates/yours/` |
| `edit-yours.html` | The Edit Form | `src/main/resources/templates/yours/` |

### The Golden Folder Map
If you are confused about where those folders actually live, here is the exact visual map of the Spring Boot structure. **If you put a file in the wrong folder, your component will not load!**

```text
restaurant-platform/
├── src/
│   ├── main/
│   │   ├── java/com/se1020/restaurant/  <-- ☕ ALL JAVA CODE GOES HERE!
│   │   │   ├── models/                  (Your YourModel.java)
│   │   │   ├── repositories/            (Your YourRepository.java)
│   │   │   ├── controllers/             (Your YourController.java)
│   │   │
│   │   └── resources/
│   │       ├── templates/               <-- 🌐 ALL HTML FILES GO HERE!
│   │       │   └── yours/
│   │       │       ├── list-yours.html
│   │       │       ├── add-yours.html
│   │       │       └── edit-yours.html
```

---

## Step 1: Build Your Model (The Database Blueprint)

1.  **Duplicate the Template:** Find `src/main/java/com/se1020/restaurant/models/User.java`. Copy the file and paste it into the same folder, naming it after your component (e.g., `Table.java` or `Review.java`).
2.  **Find & Replace:** Open your new file in IntelliJ. Press `Cmd+R` (Mac) or `Ctrl+R` (Windows):
    -   Replace `users` with `tables` (lowercase plural).
    -   Replace `User` with `Table` (capitalized singular).
3.  **Customize Variables:** Delete any User-specific fields (like `username`) and add your own variables (like `private int seatingCapacity;`). 
4.  **Update Getters/Setters:** Ensure you create standard getters and setters for every new variable you added!

---

## Step 2: Build Your Repository (The Database Connector)

This file is only **1 line of real code**!

1.  **Duplicate the Template:** Find `UserRepository.java`. Copy and paste it into the `repositories/` folder, naming it `TableRepository.java`.
2.  **Find & Replace:** Open the file and replace `User` with `Table`. 
3.  **Check Imports:** Ensure the import at the top points to your new model: `import com.se1020.restaurant.models.Table;`.

---

## Step 3: Build Your Controller (The Logic)

This file satisfies the **university rubric's \"Minimum 3 CRUD operations\"** requirement!

1.  **Duplicate the Template:** Find `UserController.java`. Copy and paste it into the `controllers/` folder, naming it `TableController.java`.
2.  **Find & Replace:** Press `Cmd+R` or `Ctrl+R` and swap everything:
    -   Replace `\"/users\"` with `\"/tables\"`.
    -   Replace `userRepository` with `tableRepository`.
    -   Replace `User` with `Table`.
3.  **Verify the Update Method:** In the `updateTable` method, ensure you are copying every single one of your custom variables (e.g., `existing.setCapacity(updated.getCapacity());`).

---

## Step 4: Build Your 3 HTML Pages (The UI)

**Do NOT write HTML from scratch!** The Team Leader has already built a beautiful, responsive UI using Tailwind CSS. If you write your own HTML, perfectly good code will look ugly and your component will fail the design consistency check.

Instead, follow these exact steps:

1. Go to `src/main/resources/templates/users/`
2. **Copy** the 3 `.html` files (`list-users.html`, `add-user.html`, `edit-user.html`)
3. Create a new folder for your component (e.g. `src/main/resources/templates/tables/`)
4. **Paste** the 3 files inside and rename them to match your component (e.g. `list-tables.html`).
5. Open each file and do a simple "Find & Replace" (Ctrl+R or Cmd+R in IntelliJ):
   - Replace `user` with `table` (lowercase)
   - Replace `users` with `tables` (plural)
   - Replace `User` with `Table` (capitalized)
6. **IMPORTANT:** Look at the table columns and the `<input th:field="*{...}">` tags. Update those variable names to actually match the ones you wrote in your `YourModel.java` file!

---

## Final Checklist Before Committing to GitHub

- [ ] My Model has `@Entity` at the top and an empty constructor `public MyModel() {}`
- [ ] My Repository extends `JpaRepository<MyModel, Integer>`
- [ ] My Controller has at least 3 CRUD operations (Read, Create, Delete)
- [ ] I copied the 3 Tailwind HTML templates into `src/main/resources/templates/mycomponent/`
- [ ] 🔴 **Crucial:** I pressed the **Red Stop Button** and **Green Play Button** in IntelliJ to restart my server!
- [ ] 🔄 **Maven Sync:** If I added any dependencies, I right-clicked `pom.xml` -> Maven -> Reload Project.
- [ ] I verified my `@RequestMapping` URL (e.g., `/tables`) and visited it at `http://localhost:8080/tables`.
- [ ] I committed my work using `git add .` and `git commit -m "message"`
