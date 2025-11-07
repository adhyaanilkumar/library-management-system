# Library Management System

A modern, full-featured Library Management System built with Spring Boot, demonstrating enterprise-level Java application development using the Spring Framework.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete books
- **Web Interface**: User-friendly HTML interface built with Thymeleaf
- **REST API**: Full RESTful API for programmatic access
- **In-Memory Database**: H2 database for easy setup and testing
- **Spring Framework**: Demonstrates core Spring concepts including:
  - Dependency Injection
  - Inversion of Control (IoC)
  - Spring Data JPA
  - Spring MVC
  - RESTful Web Services

## 📋 Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: 3.6 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (optional)

## 🛠️ Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

   Or run `LibraryManagementApplication.java` directly from your IDE.

## 🎯 Usage

### Web Interface

Once the application is running, access the web interface at:
- **Home Page**: http://localhost:8080
- **View All Books**: http://localhost:8080/books
- **Add New Book**: http://localhost:8080/books/add

### REST API

The application provides REST API endpoints for programmatic access:

#### Get All Books
```bash
GET http://localhost:8080/books/api
```

#### Get Book by ID
```bash
GET http://localhost:8080/books/api/{id}
```

#### Add New Book
```bash
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

#### Update Book
```bash
PUT http://localhost:8080/books/api/{id}
Content-Type: application/json

{
  "title": "Updated Title",
  "author": "Updated Author",
  "isbn": "978-1234567890",
  "publicationYear": 2024,
  "quantity": 3
}
```

#### Delete Book
```bash
DELETE http://localhost:8080/books/api/{id}
```

### H2 Database Console

Access the H2 database console at:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:librarydb`
- **Username**: `sa`
- **Password**: (leave empty)

## 📁 Project Structure

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

## 🏗️ Architecture

The application follows a layered architecture:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Manages database operations
- **Model Layer**: Represents data entities

## 🛠️ Technologies Used

- **Spring Boot 3.1.5**: Application framework
- **Spring Data JPA**: Database operations
- **Spring MVC**: Web framework
- **Thymeleaf**: Template engine
- **H2 Database**: In-memory database
- **Maven**: Build tool

## 📝 API Documentation

### Book Entity

```json
{
  "id": 1,
  "title": "Book Title",
  "author": "Author Name",
  "isbn": "978-1234567890",
  "publicationYear": 2024,
  "quantity": 5
}
```

### Response Codes

- `200 OK`: Successful GET/PUT request
- `201 Created`: Successful POST request
- `204 No Content`: Successful DELETE request
- `400 Bad Request`: Invalid request data
- `404 Not Found`: Resource not found

## 🧪 Testing

### Manual Testing

1. Start the application
2. Access the web interface at http://localhost:8080
3. Test CRUD operations through the UI
4. Test REST API using Postman or curl

### Sample Test Data

The application comes with sample data pre-loaded from `data.sql`:
- Java: The Complete Reference
- Spring in Action
- Effective Java
- Clean Code

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

Your Name - [@yourusername](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Framework community
- Spring Boot team
- All contributors and users

## 📚 Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [H2 Database Documentation](https://www.h2database.com/html/main.html)

---

⭐ If you find this project helpful, please consider giving it a star!
