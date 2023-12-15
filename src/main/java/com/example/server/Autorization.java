package com.example.server;


import java.sql.*;
import java.util.Scanner;

public class Autorization {

    public Autorization() {
    }


    public boolean autorizate(String userLogin, String userPassword) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from data where login = ? and password = ?");
            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, userPassword);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean newRegistration(String newLogin, String newPassword) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into data(login, password, privilege) values(?, ?, 'user');");
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, newPassword);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) { // Checking for success
                System.out.println("Registration success!");
            } else {
                System.out.println("Programmer is retard, im sorry.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean checkStatus(String userName) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select privilege from data where login = ?");
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String privilege = resultSet.getString("privilege");

                if ("admin".equals(privilege)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}