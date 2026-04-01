# 👑 GitHub Guide: Group Leader

As the Group Leader, you are responsible for setting up the main repository, inviting your team members, and making sure that the final code is merged safely without breaking everything.

## Step 0: Install Git
Before we can run any commands, you must install the actual Git software on your computer.
* Download and install Git from: **[https://git-scm.com/downloads](https://git-scm.com/downloads)**
* *(Keep all the default installation settings)*

## Step 1: Creating the Project
1. Go to [GitHub.com](https://github.com) and log in.
2. Click the **+** (plus) icon in the top right and select **New repository**.
3. Name it (e.g., `se1020-restaurant-platform`). 
4. DO NOT check "Add a README" (if you already have one locally).
5. Click **Create repository**.

## Step 2: Uploading Our Code
Open a terminal in the folder where your project is saved on your computer. Run these exact commands (replace the URL with your own repository link):

```bash
# This creates a new hidden Git history folder on your computer
git init

# This selects all your project files to be saved
git add .

# This takes a snapshot of your files
git commit -m "Initial commit of the Spring Boot project"

# This creates the 'main' branch
git branch -M main

# This links your local folder to your new GitHub repository
git remote add origin https://github.com/YOUR_USERNAME/se1020-restaurant-platform.git

# This uploads your code to the 'main' branch on GitHub
git push -u origin main
```

### ⚠️ Important: GitHub Password Error!
If GitHub asks for your password when you run `git push`, **DO NOT type your actual GitHub account password!** GitHub removed password support in 2021. Instead, you must use a **Personal Access Token (PAT)**.

**How to get your PAT:**
1. Go to GitHub.com -> Click your Profile Picture (top right) -> **Settings**.
2. Scroll to the very bottom left and click **Developer settings**.
3. Click **Personal access tokens** -> **Tokens (classic)**.
4. Click **Generate new token (classic)**.
5. Name it something like "MacBook Access".
6. Check the box that says **`repo`** (Full control of private repositories).
7. Click Generate and **COPY THE LONG PASSWORD IT GIVES YOU**.
8. Go back to your terminal, and when it asks for your password, paste that long token instead!

### ⚠️ Important: "Permission Denied" Error (Multiple GitHub Accounts)
If you get a `403 Permission denied` error or Git tries to log in with an old/different GitHub account, your computer is confused about which account to use. 
To force Git to use the correct GitHub account, run this command to update the URL (replace `YOUR_USERNAME` with your actual username):
```bash
git remote set-url origin https://YOUR_USERNAME@github.com/YOUR_USERNAME/se1020-restaurant-platform.git
```
Then try running the `git push` command again!

## Step 3: Inviting Your Team
1. On your GitHub repository page, click the **Settings** tab.
2. On the left menu, click **Collaborators**.
3. Click **Add people** and search for your group members by their GitHub username or email.
4. Tell them to check their email to accept the invitation!

## Step 4: Reviewing and Merging Code
As your team works, they will submit "Pull Requests" (PRs). A Pull Request is basically a teammate saying: *"Hey, I finished my part. Can you review it and add it to our main project?"*

1. Go to the **Pull requests** tab on your GitHub repository.
2. Click on the open pull request from your team member.
3. Click on **Files changed** to see exactly what code they wrote.
4. If it looks good, click **Merge pull request**.
5. Once merged, it is officially part of the project! You can then click **Delete branch** to clean up.

### ⚠️ Important Rule for Leaders
If you want to write your own code, you should ALSO create a new branch just like your members do. Lead by example! Never push code directly to `main`.
