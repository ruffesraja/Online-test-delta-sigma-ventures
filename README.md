# Movie Management API

A RESTful API for managing movies built with Spring Boot following Controller-Service-Model architecture.

## Prerequisites

- Java 17 or higher
- Maven (included via Maven Wrapper)

## Running the Application

### On Linux/Mac:
```bash
./mvnw spring-boot:run
```

### On Windows:
```bash
mvnw.cmd spring-boot:run
```

The server will start on `http://localhost:8080`

## API Endpoints

### 1. Add a Movie
**Endpoint:** `POST /movies`

**Parameters:**
- `name` (required) - Movie name
- `description` (required) - Movie description
- `year` (required) - Release year (must be between 1888-2100)

**Example Request:**
```bash
curl -X POST http://localhost:8080/movies -d "name=Inception&description=Sci-fi thriller&year=2010"
```

**Success Response:**
```
Movie added with ID: 10d0ef00-b685-46c8-8910-4d46d4987e82
```

**Error Response (Missing field):**
```
Missing required field: name.
```

**Error Response (Invalid year):**
```
Invalid year: must be between 1888 and 2100.
```

### 2. Get Movie by ID
**Endpoint:** `GET /movies/{id}`

**Example Request:**
```bash
curl http://localhost:8080/movies/10d0ef00-b685-46c8-8910-4d46d4987e82
```

**Success Response:**
```json
{
  "id": "10d0ef00-b685-46c8-8910-4d46d4987e82",
  "name": "Inception",
  "description": "Sci-fi thriller",
  "releaseYear": 2010
}
```

**Error Response (Not found):**
```
Movie not found
```

## Complete Testing Flow

```bash
# Step 1: Add a movie
curl -X POST http://localhost:8080/movies -d "name=The Matrix&description=Sci-fi action&year=1999"
# Output: Movie added with ID: abc123...

# Step 2: Get the movie (use the ID from step 1)
curl http://localhost:8080/movies/abc123...

# Step 3: Test validation - missing name
curl -X POST http://localhost:8080/movies -d "description=Test&year=2020"
# Output: Missing required field: name.

# Step 4: Test validation - invalid year
curl -X POST http://localhost:8080/movies -d "name=Test&description=Test&year=3000"
# Output: Invalid year: must be between 1888 and 2100.

# Step 5: Test not found
curl http://localhost:8080/movies/invalid-id
# Output: Movie not found
```

## Project Structure

```
src/main/java/com/example/test/
├── controller/
│   └── MovieController.java    # REST endpoints
├── service/
│   └── MovieService.java        # Business logic
├── model/
│   └── Movie.java               # Data model
└── TestApplication.java         # Spring Boot main class
```

## Implementation Details

- **Architecture**: Controller-Service-Model pattern
- **Storage**: In-memory ArrayList (data is lost on restart)
- **ID Generation**: UUID auto-generated for each movie
- **Validation**: 
  - Required field validation with specific error messages
  - Year range validation (1888-2100)
- **Framework**: Spring Boot 3.x with embedded Tomcat

## Technologies Used

- Java 17
- Spring Boot 3.4.2
- Spring Web (REST API)
- Maven
