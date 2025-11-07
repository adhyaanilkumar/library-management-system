# Library Management System - Spring Framework Case Study

## Abstract

This case study presents a comprehensive Library Management System developed using the Spring Framework, demonstrating core concepts of Advanced Java Programming. The system implements a complete CRUD (Create, Read, Update, Delete) application for managing books in a library. The project utilizes Spring Boot, Spring MVC, Spring Data JPA, and Thymeleaf to create a modern, web-based application with both web interface and REST API endpoints. The system showcases key Spring Framework features including Dependency Injection, Inversion of Control (IoC), Aspect-Oriented Programming concepts, and the Model-View-Controller (MVC) architectural pattern. The application uses an in-memory H2 database for data persistence, making it easy to deploy and demonstrate without external database configuration. This project serves as a practical example of how Spring Framework simplifies enterprise Java application development by providing comprehensive infrastructure support and reducing boilerplate code.

---

## Introduction

### Overview

The Spring Framework is one of the most popular and powerful frameworks for building enterprise-level Java applications. It provides comprehensive infrastructure support for developing robust, scalable, and maintainable applications. This case study demonstrates the practical implementation of Spring Framework concepts through a Library Management System.

### Objectives

The primary objectives of this case study are:

1. To demonstrate the implementation of Spring Framework core concepts
2. To showcase Spring Boot for rapid application development
3. To implement Spring Data JPA for database operations
4. To create a RESTful API using Spring MVC
5. To build a web interface using Thymeleaf templates
6. To understand Dependency Injection and Inversion of Control

### Spring Framework Concepts Demonstrated

- **Spring Boot**: Simplifies Spring application development with auto-configuration
- **Spring MVC**: Handles web requests and responses
- **Spring Data JPA**: Simplifies database operations
- **Dependency Injection**: Automatic dependency management
- **Inversion of Control (IoC)**: Spring container manages object lifecycle
- **RESTful Web Services**: API endpoints for external integration

### System Requirements

- **Java**: JDK 17 or higher
- **Maven**: 3.6 or higher
- **Spring Boot**: 3.1.5
- **Database**: H2 In-memory database
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code

---

## Coding Part

### Project Structure

```
library-management-system/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── library/
│   │   │           ├── LibraryManagementApplication.java
│   │   │           ├── controller/
│   │   │           │   ├── BookController.java
│   │   │           │   └── HomeController.java
│   │   │           ├── model/
│   │   │           │   └── Book.java
│   │   │           ├── repository/
│   │   │           │   └── BookRepository.java
│   │   │           └── service/
│   │   │               └── BookService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── data.sql
│   │       └── templates/
│   │           ├── index.html
│   │           ├── books.html
│   │           ├── add-book.html
│   │           └── edit-book.html
│   └── test/
└── README.md
```

### 1. Maven Configuration (pom.xml)

The `pom.xml` file defines project dependencies and configuration:

**Key Dependencies:**
- `spring-boot-starter-web`: For web application development
- `spring-boot-starter-data-jpa`: For JPA and database operations
- `h2`: In-memory database
- `spring-boot-starter-thymeleaf`: For template engine
- `spring-boot-devtools`: For development tools

### 2. Main Application Class

**File**: `LibraryManagementApplication.java`

```java
package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}
```

**Explanation**: 
- `@SpringBootApplication` annotation combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`
- This enables Spring Boot's auto-configuration and component scanning

### 3. Entity Class (Model)

**File**: `Book.java`

```java
package com.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "ISBN is required")
    @Column(unique = true, nullable = false)
    private String isbn;

    @NotNull(message = "Publication year is required")
    @Column(nullable = false)
    private Integer publicationYear;

    @Column(nullable = false)
    private Integer quantity = 1;

    // Constructors, Getters, and Setters
}
```

**Key Annotations:**
- `@Entity`: Marks the class as a JPA entity
- `@Table`: Specifies the table name
- `@Id`: Primary key
- `@GeneratedValue`: Auto-generation strategy for ID
- `@Column`: Column constraints and properties
- `@NotBlank`, `@NotNull`: Validation annotations

### 4. Repository Interface

**File**: `BookRepository.java`

```java
package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
```

**Explanation:**
- Extends `JpaRepository<Book, Long>` which provides CRUD operations
- Spring Data JPA automatically implements this interface
- Custom query methods follow Spring Data JPA naming conventions
- `@Repository` annotation marks it as a Spring component

### 5. Service Class

**File**: `BookService.java`

```java
package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new RuntimeException("Book with ISBN already exists");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setQuantity(bookDetails.getQuantity());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
}
```

**Key Concepts:**
- `@Service`: Marks the class as a service component
- `@Autowired`: Dependency Injection - Spring automatically injects `BookRepository`
- Business logic is encapsulated in the service layer
- Exception handling for business rules

### 6. Controller Class

**File**: `BookController.java`

```java
package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Web View - Display all books
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    // Web View - Add book form
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // Web View - Handle form submission
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // REST API - Get all books
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooksAPI() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // REST API - Add new book
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Book> addBookAPI(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Additional REST endpoints for update, delete, etc.
}
```

**Key Annotations:**
- `@Controller`: Marks the class as a Spring MVC controller
- `@RequestMapping`: Base URL mapping for all methods
- `@GetMapping`, `@PostMapping`: HTTP method mappings
- `@ModelAttribute`: Binds form data to object
- `@RequestBody`: Binds JSON request body to object
- `@ResponseBody`: Returns response as JSON
- `@Autowired`: Dependency Injection for `BookService`

### 7. Application Configuration

**File**: `application.properties`

```properties
# Server Configuration
server.port=8080

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:librarydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Thymeleaf Configuration
spring.thymeleaf.cache=false
```

**Explanation:**
- Configures H2 in-memory database
- Enables H2 console for database viewing
- Sets JPA to auto-update database schema
- Configures Thymeleaf template engine

### 8. Spring Framework Features Demonstrated

#### Dependency Injection
- `@Autowired` annotation automatically injects dependencies
- Spring container manages object creation and wiring
- Example: `BookService` is injected into `BookController`

#### Inversion of Control (IoC)
- Spring container manages the lifecycle of all beans
- Objects are created and managed by Spring, not by application code
- Reduces coupling between components

#### Spring Data JPA
- Automatic repository implementation
- Query methods based on naming conventions
- Reduces boilerplate code significantly

#### Spring MVC
- Request mapping and routing
- Model-View-Controller pattern
- Support for both web views and REST APIs

---

## Output

### Running the Application

**Steps to Run:**

1. **Prerequisites**: Ensure Java 17+ and Maven are installed

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   Or run `LibraryManagementApplication.java` directly from IDE

4. **Access the Application**:
   - Web Interface: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:librarydb`
     - Username: `sa`
     - Password: (leave empty)

### Web Interface Screenshots Description

#### Home Page
- Welcome page with navigation links
- Displays system features
- Links to view all books and add new books

#### View All Books Page
- Table displaying all books with columns:
  - ID, Title, Author, ISBN, Publication Year, Quantity
- Action buttons: Edit and Delete
- Navigation to add new books

#### Add Book Page
- Form with fields:
  - Title (required)
  - Author (required)
  - ISBN (required, unique)
  - Publication Year (required)
  - Quantity (required)
- Submit and Cancel buttons

#### Edit Book Page
- Pre-filled form with existing book data
- Update and Cancel buttons

### REST API Endpoints

The application provides REST API endpoints for programmatic access:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/books/api` | Get all books |
| GET | `/books/api/{id}` | Get book by ID |
| POST | `/books/api` | Add new book |
| PUT | `/books/api/{id}` | Update book |
| DELETE | `/books/api/{id}` | Delete book |

**Example API Request (Add Book):**
```json
POST http://localhost:8080/books/api
Content-Type: application/json

{
  "title": "Spring Framework Guide",
  "author": "John Doe",
  "isbn": "978-1234567890",
  "publicationYear": 2024,
  "quantity": 5
}
```

**Example API Response:**
```json
{
  "id": 1,
  "title": "Spring Framework Guide",
  "author": "John Doe",
  "isbn": "978-1234567890",
  "publicationYear": 2024,
  "quantity": 5
}
```

### Sample Output Screenshots

**Note**: When running the application, you will see:

1. **Console Output**:
   ```
   Library Management System started successfully!
   Access the application at: http://localhost:8080
   ```

2. **H2 Database Console**: Accessible at `/h2-console` to view database tables and data

3. **Web Pages**: Interactive HTML pages for all CRUD operations

### Testing the Application

**Manual Testing Steps:**

1. **Add a Book**:
   - Navigate to "Add New Book"
   - Fill in the form
   - Submit and verify the book appears in the list

2. **View All Books**:
   - Navigate to "View All Books"
   - Verify all books are displayed in a table

3. **Edit a Book**:
   - Click "Edit" on any book
   - Modify the information
   - Submit and verify changes

4. **Delete a Book**:
   - Click "Delete" on any book
   - Confirm deletion
   - Verify book is removed from the list

5. **Test REST API**:
   - Use Postman or curl to test API endpoints
   - Verify JSON responses

---

**End of Report**

