# Social Network Program

## Introduction

This project is a **Social Network Program** created using **Maven** and developed in **IntelliJ IDEA**. 

##what is Maven? Why Maven?
Maven is a powerful build automation and dependency management tool primarily used in Java-based projects.At its core, Maven uses a Project Object Model (POM) file (pom.xml) to define a project’s dependencies, configurations, and other essential information. Basically makes Java projects easier, so I used it.


It demonstrates the implementation of key functionalities required for a social networking platform. 
- User (object)
- Class Social Network (where all objects can interact )
- Dummy Database (to initialise a group of participants in the social network)
- Class Session (improving security)  
Based on JAVA concepts. 

---

## Project Structure

The folder and file structure of the project is as follows:

```
.idea/                     # IntelliJ project files  
src/                       
  ├── main/               
  │   ├── data/            # CSV or other data files  
  │   │   └── newcsv.csv   # Example CSV file  
  │   ├── java/org/example # Main Java source code  
  │   │   ├── entity/      # Contains entity classes  
  │   │   ├── services/    # Contains service logic  
  │   │   └── testcases/   # Unit test cases  
  │   └── resources/       # Resources directory  
  │       └── newcsv.csv   # Resource file copy  
  ├── test/                # Test files directory  
target/                    # Compiled code and build output  
.gitignore                 # Git ignore rules  
CME_proj_flowchart.pdf     # Flowchart for the project  
pom.xml                    # Maven configuration file  
README.md                  # Project documentation (this file)  

```

---

## Setup and Usage

### Prerequisites
Ensure the following tools and software are installed on your system:
- **Java Development Kit (JDK)**: Version 8 or higher  
- **Maven**: Installed and configured  
- **IntelliJ IDEA**: Recommended IDE  

---

### Steps to Open the Project  

#### 1. Clone the Repository  
Clone the project to your local machine using the following command:  
```bash
git clone https://github.com/your-username/your-repo-name.git
```

#### 2. Open the Project in IntelliJ IDEA  
1. Launch **IntelliJ IDEA**.  
2. Navigate to the project folder and open it.  
3. IntelliJ IDEA will automatically detect the Maven project and download the required dependencies.  

---

### Build and Run  

1. Open the `Main.java` file located in `src/main/java/org/example`.  
2. Run the `main()` method to start the application.  

---
Project flow goes like.
![Project Flowchart](https://github.com/user-attachments/assets/8cf608d6-eea8-41fc-8a4b-f2564a326aa8)  

---

## Features
- Unique ID Generation
- Efficient Friend add + remove
- Indexed Search (by Name, Age, Hobbies)
- User Deletion

---
