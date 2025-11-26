## 🌐 OrganizerHub – Organizer Management Backend (Spring Boot)
**OrganizerHub** is a *Spring Boot + Hibernate (JPA)* backend system built for the **RAGIR Backend Intern Assignment.**
It provides **REST APIs** to *register organizers*, *fetch organizer details*, and provide a searchable dropdown for Super Admins with full validation, pagination, and exception handling.

<br>

## ✨ Features
- ✅ Register new organizers
- ✅ Auto-generate Organizer Code (ORG00001, ORG00002, …)
- ✅ Search organizers by name, email, or phone (partial/full match)
- ✅ Pagination support for search dropdown
- ✅ DTO ↔ Entity mapping
- ✅ Custom Phone Number Validation (@ValidPhone)
- ✅ Global Exception Handling using @ControllerAdvice
- ✅ Postman Collection for testing
- ✅ MySQL/H2 compatible schema

<br>

## 📂 Project Structure
```graphql
└── com/ragir/organizer/
    ├── controller/          # REST Controllers (OrganizerController)
    ├── exception/           # GlobalExceptionHandler + custom exceptions
    ├── mapper/              # Future mappers (if needed)
    ├── model/               # Data Models
    │   ├── dto/             # Organizer DTO classes
    │   ├── entity/          # JPA Entities (Organizer)
    │   └── enums/           # Enum definitions (Status)
    ├── repository/          # Spring Data JPA repositories
    ├── service/             # Service layer
    │   └── impl/            # Implementations of services
    ├── util/                # Utility classes (OrganizerCodeGenerator)
    ├── validation/          # Custom validation system
    │   ├── annotation/      # Custom annotation (ValidPhone)
    │   └── validators/      # Validator classes (PhoneValidator)
    └── OrganizerApplication # Spring Boot main class
```

<br>

## ⚙️ Tech Stack
- ``Backend:`` Java 17, Spring Boot 3.x
- ``Database:`` MySQL / H2
- ``ORM:`` Hibernate (JPA)
- ``Build Tool:`` Maven
- ``Testing:`` Postman
- ``Documentation:`` (Optional) Swagger UI

<br>

<a name="api-endpoints"></a>
## 🚀 API Endpoints
### 🔹 Organizer Management APIs

| **Method** | **Endpoint**                   | **Description**                  |
|------------|--------------------------------|----------------------------------|
| **POST**   | `/api/organizers`              | Register a new organizer         |
| **GET**    | `/api/organizers/{id}`         | Get organizer by ID              |
| **GET**    | `/api/organizers/search?q=abc` | Search organizers (dropdown API) |


### 🔹 Example Requests
➤ Register Organizer
```http
POST /api/organizers
Content-Type: application/json

{
  "name": "Ragir Technologies",
  "email": "info@ragir.com",
  "phone": "9876543210",
  "businessType": "IT Services"
}
```

➤ Get Organizer by ID
```http
GET /api/organizers/1
```

➤ Search Organizers
```http
GET /api/organizers/search?q=Ragir&page=0&size=10
```

<br>

## 🛡️ Exception Handling
The application uses a global exception handling mechanism with `@ControllerAdvice` to manage errors gracefully.

- `ResourceNotFoundException` → 404 NOT FOUND (for missing organizers)
- `InvalidInputException` → 400 BAD REQUEST (for validation errors)
- `MethodArgumentNotValidException` → 400 BAD REQUEST (for validation errors)
- `ConstraintViolationException` → 400 BAD REQUEST (for custom validation errors)
- `Generic exceptions` → 500 INTERNAL SERVER ERROR

Error Response Examples:
```json
{
  "timestamp": "2025-11-27T00:45:22",
  "path": "/api/organizers",
  "error": "Validation Failed",
  "status": 409,
  "details": {
    "email": "Email already exists"
  }
}
```

<br>

<a name="how-to-run"></a>
## ▶️ How to Run

#### 1. Clone Repository
```bash
git clone https://github.com/gyarsilalsolanki011/OrganizerHub.git
cd OrganizerHub
```

#### 2. Set up MySQL Database
**Create MySQL database named `organizer_db`**
```sql
CREATE DATABASE organizer_db;
```

#### 3. Configure MySQL DB in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/organizer_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
#### 4. Run application
```bash
mvn spring-boot:run
```
OR
```bash
mvn package
java -jar target/spring-boot-ragir-organizer.jar
```

#### 5. Access APIs:
- Base URL → `http://localhost:8080/api/organizers`
- Swagger → `http://localhost:8080/swagger-ui.html`

