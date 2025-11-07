# Quick Start Instructions

## Prerequisites
- Java JDK 17 or higher
- Maven 3.6 or higher

## How to Run

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   Or run `LibraryManagementApplication.java` directly from your IDE

3. **Access the application:**
   - Web Interface: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:librarydb`
     - Username: `sa`
     - Password: (leave empty)

## Note
If you encounter any issues with `pom.xml`, change line 18 from `<n>` to `<name>`.

## Report Content
The README.md file contains all the content you need for your case study report. Simply copy the sections into your Word document.

