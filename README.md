# TASK 1:

**Service 1 (Orchestrator)**: Port 8080

**Service 2 (Greeting)**: Port 8081

**Service 3 (Concat)**: Port 8083

## Commands to start each service(In same seq):

Service 2 in a new terminal: cd service2-greeting -> mvn spring-boot:run

Service 3 in another new terminal: cd service3-concat -> mvn spring-boot:run

Service 1 in another new terminal: cd service1-orchestrator -> mvn spring-boot:run

## Postman testing:
### Service 1:

URL:  http://localhost:8080/api/v1/health

Method: GET

### Service 2:

URL: http://localhost:8081/api/v1/greeting

Method: GET

### Service 3:

URL: http://localhost:8083/api/v1/concat

Method: POST

Header: Content-Type: application/json

Body: {"Name": "John", "Surname": "Doe"}


### Service 1: Orchestrate

URL: http://localhost:8080/api/v1/orchestrate

Method:POST

Header: Content-Type: application/json , X-Trace-Id: test-123

Body: {"Name": "John", "Surname": "Doe"}

# TASK 2:
**Service 4 (Database)**: Port 8085

## Commands to start

Service 4 in another new terminal: cd service4-database -> mvn spring-boot:run
            
## Postman testing:
### Populate data:

URL: http://localhost:8085/api/v1/character-classes/populate

Method: POST

### Nested Data:

URL: http://localhost:8085/api/v1/character-classes/nested

Method: GET

### Get by ID:

URL: http://localhost:8085/api/v1/character-classes/5

Method:GET

## H2 console:

URL:  http://localhost:8085/h2-console

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: password

### Refer to Unit test Doc for testing screenshots
