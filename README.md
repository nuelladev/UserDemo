# User Demo Application

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Activity Logging](#activity-logging)
- [Contributing](#contributing)


## Overview

The User Demo Application is a Java Spring Boot project that demonstrates basic CRUD (Create, Read, Update, Delete) operations for managing user data. This application connects to a MySQL database to store and retrieve user information.

## Prerequisites

Before you begin, make sure you have the following prerequisites:

- **Java**: Install a Java Development Kit (JDK). You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or use [OpenJDK](https://openjdk.java.net/).

- **Spring Boot**: Ensure that you have Spring Boot installed or add Spring Boot dependencies to your project using [Spring Initializer](https://start.spring.io/).

- **MySQL**: Install and run a MySQL database server. You can download it from the [official website](https://dev.mysql.com/downloads/).

- **Postman** (optional): Install [Postman](https://www.postman.com/downloads/) to test RESTful API endpoints.

## Installation

1. **Clone the Repository:**

   Clone this repository to your local machine:

   ```bash
   git clone https://github.com/your-username/UserDemo.git
   
## Configuration
Before running the application, configure the database connection settings. Modify the following properties in the application.properties file located in the src/main/resources folder:

# Database connection settings
spring.datasource.url=jdbc:mysql://localhost:3306/databasename
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
Replace YOUR_PASSWORD with your MySQL root password.

## Usage
Build the Project
Build the project using Maven:

./mvnw clean install
The application will be accessible at http://localhost:8080. Ensure that your MySQL server is running.

## API Endpoints
GET /app/{name}: Retrieve a user by their name.
GET /app/all: Retrieve all users.
GET /app/sorted: Retrieve all users sorted by name.
POST /app/add: Add a new user. Send a JSON request body with user details.
GET /app/activity: Retrieve the activity log (Note: Log content is a placeholder).
Activity Logging
The application logs activities to an "activity.log" file located in the project root directory. Each log entry includes a timestamp and a description of the activity.

## Contributing
Contributions are welcome! To contribute to this project:

Fork the project.
Create a new branch: git checkout -b feature/your-feature-name.
Commit your changes: git commit -m "Your commit message here".
Push your changes: git push origin feature/your-feature-name.
Create a pull request to the main branch of the original repository.






