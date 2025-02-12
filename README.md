# 🔍 Search and Sort Project

## 📖 Overview
This project implements **multiple sorting algorithms** and measures **time and space complexity** for each method.  
It is built using **Java** and **Maven**, making it easy to manage dependencies, build, and test.

The project reads input from a **CSV file containing test cases**, applies sorting algorithms dynamically,  
and records execution details in an **output CSV file**.

---

## 🏗️ Maven Integration
This project is managed using **Apache Maven** for:
- **Dependency management**
- **Automated builds**
- **Unit testing**

### **Maven Commands**
```sh
# Compile the project
mvn clean compile

# Run the project
mvn exec:java -Dexec.mainClass="SearchandSort.App"

# Run unit tests
mvn test

 Implemented Sorting Methods
Basic Sorting Algorithms
✔️ Bubble Sort - Compares adjacent elements and swaps if needed.
✔️ Selection Sort - Finds the smallest element and swaps it into place.
✔️ Insertion Sort - Inserts elements at the correct position in a sorted list.

Divide & Conquer Sorting
✔️ Merge Sort - Recursively divides and merges sorted halves.
✔️ Quick Sort - Uses pivoting to partition and sort recursively.

Efficient Sorting
✔️ Heap Sort - Uses a priority queue (heap) to sort elements efficiently.

📥 Installation & Setup
Follow these steps to download and run the project on Mac or Windows.

🔹 Prerequisites
✔️ Install Java (JDK 21)

Download JDK and install it.
Verify Java installation:
java -version
✔️ Install Maven
Download and install from: https://maven.apache.org/download.cgi
Verify Maven installation:
mvn -version
✔️ Install Git (For Cloning Repo)
Download Git: https://git-scm.com/downloads
Verify Git installation:
git --version
🚀 How to Download & Run the Project
🔹 Step 1: Clone the Repository
git clone https://github.com/vindybaba/SearchAndSort.git
cd SearchAndSort
🔹 Step 2: Compile the Project
mvn clean compile
🔹 Step 3: Run the Project
mvn exec:java -Dexec.mainClass="SearchandSort.App"
🔹 Step 4: Run Unit Tests
mvn test
🔹 Step 5: Check the Output
The sorted results will be stored in:
src/main/resources/output_results.csv
📌 Git Setup & Contribution Guide
🔹 Set Up Git (Windows & Mac)
If Git is not installed, download and install it from Git SCM.
1️⃣ Configure Git
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
2️⃣ Add & Commit Changes
git add .
git commit -m "Your commit message"
3️⃣ Push Changes to GitHub
git push origin main
4️⃣ If "Failed to Push" Error Occurs
Try pulling first, then pushing:
git pull origin main --rebase
git push origin main
