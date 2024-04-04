package com.example.server;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    public boolean newBook(String author, String name, String genre, String owner) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into books(author, name,genre,owner) values(?, ?,?,?);");
            preparedStatement.setString(1, author);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, owner);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) { // Checking for success
                System.out.println("Book has added!");
            } else {
                System.out.println("Programmer is retard, im sorry.");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<String> showBooks(){
        List<String> books = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from books;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                books.add(resultSet.getString("name"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    public boolean delBook(String name,String owner) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from books where name=? and owner=?;");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) { // Checking for success
                System.out.println("Book has added!");
            } else {
                System.out.println("Programmer is retard, im sorry.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
