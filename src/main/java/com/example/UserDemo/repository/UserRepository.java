package com.example.UserDemo.repository;

import com.example.UserDemo.model.User;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users_db";
    private static final String DB_USER = "****";
    private static final String DB_PASSWORD = "****";
    private static final String LOG_FILE = "activity.log";

    // SQL statement to create the "users" table
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "age INT," +
                    "account_balance DOUBLE," +
                    "location VARCHAR(255)" +
                    ")";

    public UserRepository() {
        // Initialize database and create table if it doesn't exist
        initializeDatabase();
    }

    private void initializeDatabase() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            // Execute the create table statement
            statement.executeUpdate(CREATE_TABLE_SQL);

            // Log initialization activity
            logActivity("Database initialized and table created.");
        } catch (SQLException e) {
            logActivity("Error occurred while initializing the database: " + e.getMessage());
        } finally {
            closeResources(null, statement, connection);
        }
    }

    public User getUser(String name) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE name = '" + name + "'";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setAccountBalance(resultSet.getDouble("account_balance"));
                user.setLocation(resultSet.getString("location"));
            }

            // Log user retrieval activity
            logActivity("User retrieved: " + name);
        } catch (SQLException e) {
            logActivity("Error occurred while retrieving user: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setAccountBalance(resultSet.getDouble("account_balance"));
                user.setLocation(resultSet.getString("location"));
                users.add(user);
            }

            // Log user retrieval activity
            logActivity("All users retrieved.");
        } catch (SQLException e) {
            logActivity("Error occurred while retrieving users: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return users;
    }

    public List<User> getSortedUsers() {
        List<User> users = getAllUsers();
        users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));

        // Log sorting activity
        logActivity("Users sorted.");

        return users;
    }

    public void addUser(User user) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
            String insertQuery = "INSERT INTO users (name, age, account_balance, location) VALUES ('" +
                    user.getName() + "', " + user.getAge() + ", " + user.getAccountBalance() +
                    ", '" + user.getLocation() + "')";
            statement.executeUpdate(insertQuery);

            // Log user addition activity
            logActivity("User added: " + user.getName());
        } catch (SQLException e) {
            logActivity("Error occurred while adding a user: " + e.getMessage());
        } finally {
            closeResources(null, statement, connection);
        }
    }

    private void logActivity(String activity) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true)) {
            fileWriter.write(activity + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
