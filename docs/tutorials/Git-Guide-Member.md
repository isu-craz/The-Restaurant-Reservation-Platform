# 👩‍💻 GitHub Guide: Team Members

Welcome to the team! If you are new to Git and GitHub, this guide will walk you through exactly how to safely download the code, write your part of the project, and upload it without breaking anyone else's work.

## What is Git? What is GitHub?
* **Git** is a tool installed on your computer that tracks the history of your code. It's like a time machine for your files.
* **GitHub** is a website where you store your Git history online, so your team can share and collaborate.

## Step 0: Install Git
Before you can do anything, you must install the Git software on your computer.
* Download and install Git from: **[https://git-scm.com/downloads](https://git-scm.com/downloads)**
* *(Just click 'Next' through all the default installation options)*

## Step 1: Downloading the Project (Cloning)
You only need to do this ONCE.
1. Ask your group leader for the repository link (e.g., `https://github.com/USERNAME/se1020-restaurant-platform`).
2. Open IntelliJ IDEA -> Click **Clone Repository** (as seen on the welcome screen).
3. Paste the URL into the box. **(Make sure the "Shallow clone / truncated history" box is completely UNCHECKED!)**
4. Click **Clone**. You now have the project on your computer!

## Step 2: 🛑 THE GOLDEN RULE 🛑
**NEVER write code on the `main` branch!** The `main` branch is the finished, working product. 

Instead, you need to create a **Branch**. Think of a branch like making a copy of a document so you can edit it without ruining the original. 

Before you start writing code, open your terminal (in IntelliJ, the Terminal tab is at the bottom) and type:
```bash
# First, make sure you have the newest updates from the team
git pull origin main

# Second, make your own copy (branch) to work on. Give it a good name!
git checkout -b your-name/what-you-are-building
# Example: git checkout -b ashen/table-management
```

## Step 3: Saving Your Work
Now, write your Java code! Once you have finished a feature and tested that it works, you need to save it.

In Git, saving happens in two steps:
1. **`git add .`** -> This tells Git: "Hey, look at all the files I changed."
2. **`git commit -m "Your message"`** -> This tells Git: "Take a snapshot of these files and save them to my history with this label."

```bash
# Run this to select all changed files
git add .

# Run this to take the snapshot (be descriptive!)
git commit -m "Created Table class and the Tables HTML page"
```

## Step 4: Sharing Your Work
Your code is saved on your computer, but your team can't see it yet. You need to push your branch up to GitHub.

```bash
# This magic command takes whatever branch you are currently on and safely uploads it!
git push -u origin HEAD
```

> ### ⚠️ Important: GitHub Password Error!
> If your terminal asks for a password and then completely fails when you type it, it is because GitHub disabled standard passwords. 
> You must generate a **Personal Access Token (PAT)**. 
> *To see exactly how to generate one (using the "**Classic**" token and checking the **`repo`** box), ask your Group Leader to show you the instructions in Step 2 of [`Git-Guide-Leader.md`](Git-Guide-Leader.md)!*

> ### ⚠️ Important: "Permission Denied" Error (Multiple GitHub Accounts)
> If you get a `403 Permission denied` error when pushing, your computer might be trying to log in with an old GitHub account saved on your machine.
> Force it to use your current account by running this command (replacing `YOUR_USERNAME` with your actual username and `GROUP_LEADER_USERNAME` with the leader's username):
> `git remote set-url origin https://YOUR_USERNAME@github.com/GROUP_LEADER_USERNAME/se1020-restaurant-platform.git`
> Then try pushing again!

## Step 5: The Pull Request
1. Go to the project page on GitHub.com.
2. You will see a green button that says **Compare & pull request** next to your branch name. Click it!
3. Add a title and description explaining what you built.
4. Click **Create pull request**.

Now, your Group Leader will review your code. If it's good, they will merge it into the `main` branch for everyone to use.

## Summary Checklist for Every Coding Session
1. `git pull origin main` (Get latest code)
2. `git checkout -b my-new-feature` (Create a branch)
3. *(Write your code and test it)*
4. `git add .` (Stage files)
5. `git commit -m "I did a thing"` (Save files)
6. `git push -u origin HEAD` (Upload files magically)
7. Create a Pull Request on GitHub!