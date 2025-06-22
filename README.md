#  Student Course Registration System

A Spring Boot-based REST API for managing students, courses, and their registrations — built for the ZDATA Innovations Backend Developer Internship Assessment.

---

##  How to Run the Application

### 🛠 Requirements
- Java 17
- Maven 
- Spring Boot 3.2.4
- Optional: Docker

###  Run with Maven
```bash
mvn clean install
mvn spring-boot:run
```

###  Swagger UI
Once running, access Swagger Docs at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Sample HTTP Requests (Postman or curl)

###  Register a Student
```http
POST /students
Content-Type: application/json

{
  "name": "Alice",
  "email": "alice@example.com"
}
```

### Add a Course
```http
POST /courses
Content-Type: application/json

{
  "code": "CS101",
  "title": "Intro to Computer Science",
  "instructor": "Dr. Smith"
}
```

### Register Student to Course
```http
POST /students/{studentId}/register/{courseId}
```

### Drop Course
```http
DELETE /students/{studentId}/drop/{courseId}
```

###  Get Student's Registered Courses
```http
GET /students/{studentId}/courses
```

###  Get All Courses (With Pagination)
```http
GET /courses?page=0&size=5
```

---

##  Assumptions Made

- Email and course code are both unique (handled with 409 Conflict).
- A student can't register to the same course twice.
- A student can only drop a course they are registered in.
- There are no seat limits on courses.
- In-memory storage only — no external DB used.
- DTOs and validations are used with `@Valid`, `@Email`, `@NotBlank`.
- HTTP status codes follow REST standards:  
  `400`, `404`, `409`, and `500` (fallback).

---

## Code Structure

- **Controller:** Handles REST API endpoints  
- **Service:** Business logic, validation, and in-memory storage  
- **DTOs:** Input/output data transfer objects  
- **Models:** Internal domain models  
- **Exception:** Custom error handling  
- **Config:** Swagger OpenAPI setup  

---

## Test Coverage Summary

Unit tests are written using **JUnit 5** under `src/test/`.

###  Covered Scenarios:
- Registering new students
- Adding new courses
- Preventing duplicate emails/course codes
- Registering and dropping courses
- Handling invalid student/course IDs
- Ensuring proper exception handling

### Run Tests
```bash
mvn test
```

Sample output:
```
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
```

> Tests are focused on service layer, using in-memory logic.

---

##  Docker Support (Optional)

### Dockerfile Included
```bash
# Build Docker image
docker build -t course-app .

# Run container
docker run -p 8080:8080 course-app
```

---

##  Project Author

Developed as part of the ZDATA Backend Intern Assessment.  
Fully written by Chandimal Priyamantha.

## Optional
Azure CI/CD Hosting Link:
```http
https://zdataapp-fdghabhue7bfhrav.canadacentral-01.azurewebsites.net/swagger-ui/index.html
```
