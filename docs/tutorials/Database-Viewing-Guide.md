# 🔍 How to View Your SQLite Database

When your Java code saves a `User` or a `Table`, you will want to physically look inside the database to make sure it saved correctly! You have two easy ways to do this.

---

## Option 1: Using IntelliJ Database Tab (Fastest)

If you have IntelliJ IDEA Ultimate (free for students), you can see your data directly inside your coding screen!

1. Look at the very far **Right Edge** of your IntelliJ IDEA window. You will see a vertical tab called **Database**. Click it.
2. Click the tiny **+ (Plus) Button** at the top of that panel.
3. Select **Data Source** -> **SQLite**.
4. In the pop-up window, find the "File" box, click the little folder icon, and find the `restaurant.sqlite` file located directly inside your active project folder.
5. Click **OK**. 

You will now see a tree of your tables on the right side of the screen. Simply double-click on `users` (or any other table) and it will open in the middle of your screen perfectly formatted like an Excel spreadsheet!

---

## Option 2: Using SQLiteStudio (Best Alternative)

If you are using IntelliJ Community or VS Code, you should use the free SQLiteStudio program!

1. Download and install **[SQLiteStudio](https://sqlitestudio.pl/)**.
2. Open the program.
3. In the top menu, click **Database -> Add a Database**.
4. Click the tiny folder icon to browse your computer. Search your folders for the `restaurant.sqlite` file sitting inside your active project directory.
5. Once added, you will see it show up on the left menu.
6. Double-click on the database, double-click on the `users` table, and then click the **Data** tab at the top of the screen to view your information!
