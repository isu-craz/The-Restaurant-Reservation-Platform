# ☕ Java 17 Installation Guide

Since we are building a Java backend project, every single team member must have the **Java Development Kit (JDK) 17** installed on their computer so that IntelliJ and Spring Boot can run seamlessly.

## 1. Why exactly Java 17?
We specifically require **Java 17** because:
- **It is Long Term Support (LTS):** Meaning it is a highly stable, rock-solid release that is perfectly compatible with the modern Spring Boot 3 framework we are using.
- **University Standard:** Most university grading machines run Java 17. If you write code using the absolute newest version (like Java 25), your code might completely crash when the lecturer tries to run it. If we all agree on Java 17 as a team, our laptops will be perfectly synchronized.

## 2. Why Eclipse Temurin (Adoptium)?
Instead of downloading Java through Oracle, we highly recommend getting it from [Adoptium.net](https://adoptium.net/temurin/releases/?version=17) (specifically the "Eclipse Temurin" build). 
1. It is 100% free and open-source software (no account needed).
2. It guarantees the cleanest, industry-standard setup.
3. Most importantly: It **automatically sets up your Environment Variables (`JAVA_HOME`)** out of the box, which is usually the biggest headache when beginners install Java!

---

## 3. How to Install (For Windows & Mac)

### Step 1: Download the Installer
Go to this link: 👉 **[Adoptium: Download Java 17](https://adoptium.net/temurin/releases/?version=17)**. 
- The website will automatically detect if you are on a Windows or macOS computer. 
- Download the standard installer file provided (`.msi` for Windows or `.pkg` for Mac).

### Step 2: Run the Setup
Run the file you just downloaded. 
*   **🚨 Windows User Alert:** When the setup window asks what "custom features" you want to install, make sure that the option for **"Set JAVA_HOME variable"** is checked or enabled. (Sometimes it defaults to a red "X", you must click it and select *"Will be installed on local hard drive"*).

---

### Alternative: Install via Terminal (For Advanced Users)
If you are already comfortable with the terminal, you can skip the website download completely! Just open your terminal and paste one of these commands:

**For Mac (using Homebrew):**
```bash
brew install --cask temurin@17
```

**For Windows (using Winget):**
```powershell
winget install --id EclipseAdoptium.Temurin.17.JDK
```

---
### Step 3: Verify the Installation!
To make absolutely sure everything worked flawlessly, open your computer's terminal (search for "Command Prompt" or "PowerShell" on Windows, or "Terminal" on Mac) and type this exact command:

```bash
java -version
```

If the installation was successful, your computer will print back a confirmation that looks something like this:
```text
openjdk version "17.0.10" 2024-01-16
OpenJDK Runtime Environment Temurin-17.0.10+7 (build 17.0.10+7)
OpenJDK 64-Bit Server VM Temurin-17.0.10+7 (build 17.0.10+7, mixed mode)
```

> **🚨 What if my terminal shows Java 21 or Java 25 instead?**
> Do not panic! If your terminal prints a higher version number, it simply means your computer already has another version of Java installed from a previous course, and the terminal is prioritizing the highest number.
> **Java 17 was still successfully successfully installed in the background!** You don't have to uninstall your old Java versions. We will manually select the new Java 17 installation when we open IntelliJ IDEA later on.
> 
> **How to definitively prove Java 17 was installed:**
> * **On Mac:** Type `/usr/libexec/java_home -V` in the terminal. It will list every Java version installed. As long as `17` is in that list, you are perfect!
> * **On Windows:** Open Command Prompt and type `dir "C:\Program Files\Eclipse Adoptium"`. As long as you see a `jdk-17...` folder listed, you are perfect!

You are completely finished! Return to the [**Project Roadmap**](../project-roadmap.md) and cross off the first step.
