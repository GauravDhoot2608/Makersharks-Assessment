# Makersharks-Assessment

A REST API for managing suppliers.

## Table of Contents
- [Requirements](#requirements)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Example CURL Commands](#example-curl-commands)
- [Swagger API Documentation](#swagger-api-documentation)
- [Security Setup](#security-setup)


## Requirements

- Java 17 or later
- Maven
- Spring Boot
- MySQL

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/GauravDhoot2608/Makersharks-Assessment.git
   ```
   
2. Navigate to the project directory:
  ```bash
  cd Makersharks-Assessment
  ```

3. Build and run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

4. The application will start on
    ```bash
    http://localhost:8080
    ```

## API Endpoints

### Get Suppliers

**Endpoint:** `GET /api/supplier/query`

**Query Parameters:**

- `location` (optional): Location of the supplier
- `natureOfBusiness` (optional): Nature of business of the supplier
- `manufacturingProcess` (optional): Manufacturing process used by the supplier
- `page` (optional, default=`0`): Page number for pagination
- `size` (optional, default=`10`): Number of results per page

## Example CURL Commands

1. **Get all the suppliers whose location is Surat, nature of business is small scale, and manufacturing process is moulding:**

    ```bash
    curl -X GET "http://localhost:8080/api/supplier/query?location=Surat&natureOfBusiness=SMALL_SCALE&manufacturingProcess=MOULDING"
    ```

2. **Get all the suppliers whose manufacturing process is 3D printing:**

    ```bash
    curl -X GET "http://localhost:8080/api/supplier/query?manufacturingProcess=PRINTING_3D"
    ```

3. **Get all the suppliers whose location is Delhi:**

    ```bash
    curl -X GET "http://localhost:8080/api/supplier/query?location=Delhi"
    ```

## Swagger API Documentation
   ```bash
   http://localhost:8080/swagger-ui/index.html#/
   ```

## Security Setup

complete security consideration for the project, follow these steps:

1. **Authentication and Authorization**
   - Implement JWT for user authentication. 
     - Add JWT dependencies in pom.xml.
     - Create Security Config
     - Create a JWT Authentication filter.
     - Create JWT Service
     - Create a Custom User Details Service
     - Add login and token generation endpoints.
   - Define roles and permissions.
     - Use Spring Security’s `@PreAuthorize` and `@Secured` annotations.

     ```java
     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/admin")
     public ResponseEntity<String> getAdminData() {
         return ResponseEntity.ok("Admin data");
     }
     ```

2. **HTTPS for Secure Communication**
   - Configure HTTPS in Spring Boot.
     - Obtain an SSL certificate from a CA.
     - Update `application.properties` or `application.yml`:

3. **Data Encryption at Rest**
   - Encrypt sensitive data using strong algorithms.
   - Manage keys securely with tools like AWS KMS or HashiCorp Vault.

4. **Rate Limiting**
   - Implement rate limiting with Spring Security’s `@RateLimiter` or Bucket4j.
   - Configure limits based on endpoints and user roles.

5. **Security Event Logging**
   - Log security events with Spring Boot’s logging configuration

     ```properties
     logging.level.org.springframework.security=DEBUG
     ```

