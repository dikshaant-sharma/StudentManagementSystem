# Student Management System

A robust and secure Spring Boot REST API for managing student records, fully integrated with MongoDB Atlas (Cloud Database). Built using Java 21 records for lightweight DTO mappings and standard bean validation.

---

## Features

- **Full CRUD Endpoints**: Operations to create, read, update, and delete students.
- **Strict Data Validation**: Input constraints checking for valid emails, mandatory fields, and logical GPA boundaries (0.0 to 4.0).
- **Duplicate Email Prevention**: Business logic verification that prevents registration of duplicate email addresses.
- **Centralized Error Handling**: Standardized, clean JSON error messages for validation violations, missing resources, and database exceptions.
- **Docker/Production Ready**: Supports dynamic connection override through system environment variables.

---

## Tech Stack

- **Java**: 21+ (Compatible up to Java 25)
- **Framework**: Spring Boot 3.4.1 (Spring Web, Spring Data MongoDB, Jakarta Validation)
- **Database**: MongoDB Atlas Cloud
- **Build Tool**: Maven (Wrapper preconfigured)

---

## Directory Structure

```
studentmanagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── studentmanagement/
│   │   │       ├── controller/
│   │   │       │     └── StudentController.java
│   │   │       ├── service/
│   │   │       │     ├── StudentService.java
│   │   │       │     └── StudentServiceImpl.java
│   │   │       ├── repository/
│   │   │       │     └── StudentRepository.java
│   │   │       ├── entity/
│   │   │       │     └── Student.java
│   │   │       ├── dto/
│   │   │       │     ├── StudentRequest.java
│   │   │       │     └── StudentResponse.java
│   │   │       ├── exception/
│   │   │       │     ├── GlobalExceptionHandler.java
│   │   │       │     └── ResourceNotFoundException.java
│   │   │       └── StudentManagementApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── studentmanagement/
│               └── StudentManagementApplicationTests.java
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

## API Endpoints

All responses are serialized as standard JSON.

### 1. Create a Student
- **Endpoint**: `POST /api/students`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "course": "Computer Science",
    "gpa": 3.85
  }
  ```
- **Response (`201 Created`)**:
  ```json
  {
    "id": "6a4bc5fc134bcf057990f477",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "course": "Computer Science",
    "gpa": 3.85,
    "enrollmentDate": "2026-07-06"
  }
  ```

### 2. Retrieve All Students
- **Endpoint**: `GET /api/students`
- **Response (`200 OK`)**:
  ```json
  [
    {
      "id": "6a4bc5fc134bcf057990f477",
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "course": "Computer Science",
      "gpa": 3.85,
      "enrollmentDate": "2026-07-06"
    }
  ]
  ```

### 3. Get Student by ID
- **Endpoint**: `GET /api/students/{id}`
- **Response (`200 OK`)**:
  ```json
  {
    "id": "6a4bc5fc134bcf057990f477",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "course": "Computer Science",
    "gpa": 3.85,
    "enrollmentDate": "2026-07-06"
  }
  ```

### 4. Update Student
- **Endpoint**: `PUT /api/students/{id}`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "course": "Software Engineering",
    "gpa": 3.92
  }
  ```
- **Response (`200 OK`)**:
  ```json
  {
    "id": "6a4bc5fc134bcf057990f477",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "course": "Software Engineering",
    "gpa": 3.92,
    "enrollmentDate": "2026-07-06"
  }
  ```

### 5. Delete Student
- **Endpoint**: `DELETE /api/students/{id}`
- **Response (`200 OK`)**:
  *(Empty Response)*

---

## Error Handling Scenarios

### Validation Failure (`400 Bad Request`)
Returned when the incoming request fails model constraints:
```json
{
  "message": "Validation failed",
  "errors": {
    "firstName": "First name is required",
    "gpa": "GPA cannot be more than 4.0",
    "email": "Email should be valid"
  }
}
```

### Duplicate Email Registration (`400 Bad Request`)
Returned when an email address is already registered in the system:
```json
{
  "error": "Email is already in use"
}
```

### Resource Not Found (`404 Not Found`)
Returned when fetching or operating on a student ID that does not exist in MongoDB:
```json
{
  "error": "Student not found with id: 6a4bc5fc134bcf057990f477"
}
```

---

## Getting Started

### Prerequisites
- **Java**: JDK 21 or higher installed.

### Configuration
By default, the application is preconfigured to connect to the developer cluster. If you want to supply your own custom MongoDB connection URL, set the `SPRING_DATA_MONGODB_URI` environment variable before running the application:

#### Windows (PowerShell)
```powershell
$env:SPRING_DATA_MONGODB_URI="mongodb+srv://<username>:<password>@cluster0.3v4y4.mongodb.net/your_db"
```

#### Linux / macOS
```bash
export SPRING_DATA_MONGODB_URI="mongodb+srv://<username>:<password>@cluster0.3v4y4.mongodb.net/your_db"
```

---

## Running Locally

1. **Clone the repository**:
   ```bash
   git clone <your-repository-url>
   cd StudentManagementSystem
   ```

2. **Clean and Compile**:
   ```bash
   # Linux/macOS
   ./mvnw clean compile
   
   # Windows
   .\mvnw.cmd clean compile
   ```

3. **Start the Application**:
   ```bash
   # Linux/macOS
   ./mvnw spring-boot:run
   
   # Windows
   .\mvnw.cmd spring-boot:run
   ```
   The application will start on port `8080`.

4. **Verify Application Status**:
   Send a test request via curl or PowerShell:
   ```bash
   curl http://localhost:8080/api/students
   ```
