package com.example.client;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//package com.example.client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main {
    private static String userLogin;
            public static void main(String[] args) {
                Socket socket = null;
                InputStreamReader inputStreamReader = null;
                OutputStreamWriter outputStreamWriter = null;
                BufferedReader bufferedReader = null;
                BufferedWriter bufferedWriter = null;
                try {
                    socket = new Socket("localhost", 1234);
                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    bufferedWriter = new BufferedWriter(outputStreamWriter);
                    Scanner scanner = new Scanner(System.in);
                    String tempVar1, tempVar2, tempVar3, msgFromServer, command;
                    int option = 0;
                    System.out.println("Hi, choose an option: 1 - Login, 2 - Registration" );
                    option = scanner.nextInt();
                    scanner.nextLine();
                    while (true) {
                        if (option == 1) {
                            command = "Login";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Write down your data of account\nLogin:");
                            userLogin = scanner.nextLine();
                            bufferedWriter.write(userLogin);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Password: ");
                            String password = scanner.nextLine();
                            bufferedWriter.write(password);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true"))
                                System.out.println("Welcome!");
                            else {
                                System.out.println("Wrong login or password. Try again later.");
                            }
                        } else if (option == 2) {
                            command = "Registration";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Write down login and password to create new account for you: \nLogin: ");
                            String login = scanner.nextLine();
                            bufferedWriter.write(login);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Password: ");
                            String password = scanner.nextLine();
                            bufferedWriter.write(password);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true")) {
                                System.out.println("Registration succees!");
                                break;
                            }
                        }
                         /*   else{
                                System.out.println("You haven't chose an option. Try again later.");
                                break;
                        }*/


                        //System.out.println(msgFromServer + "123");

                        System.out.println("Welcome to the library! Choose an option you want to:\n11 - Add new Book\n12 - Show all books\n21 - (Admin)Add new book to specific user\n22 - (Admin)Delete book\n(Admin)23 - Add new user\n0 - exit");
                        option = scanner.nextInt();
                        scanner.nextLine();
                        if (option == 11) {
                            command = "newBook";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            bufferedWriter.write(userLogin); //tempvar1 with userName
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Write down author name using '_': ");
                            tempVar1 = scanner.nextLine();
                            bufferedWriter.write(tempVar1);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Write down book name using '_': ");
                            tempVar2 = scanner.nextLine();
                            bufferedWriter.write(tempVar2);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.println("Write down genre of the book: ");
                            tempVar3 = scanner.nextLine();
                            bufferedWriter.write(tempVar3);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true")) {
                                System.out.println("The book has added!");
                            }
                        } else if (option == 21) {
                            System.out.println("sss: " + userLogin);
                            command = "statusCheck";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            bufferedWriter.write(userLogin);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true")) {
                                command = "newBook";
                                System.out.println("To which user? Write userName: ");
                                String userName = scanner.nextLine();
                                bufferedWriter.write(command); //tempvar1 with userName
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                bufferedWriter.write(userName); //tempvar1 with userName
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Write down author name using '_': ");
                                tempVar1 = scanner.nextLine();
                                bufferedWriter.write(tempVar1);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Write down book name using '_': ");
                                tempVar2 = scanner.nextLine();
                                bufferedWriter.write(tempVar2);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Write down genre of the book: ");
                                tempVar3 = scanner.nextLine();
                                bufferedWriter.write(tempVar3);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                msgFromServer = bufferedReader.readLine();
                                if (msgFromServer.equals("true")) {
                                    System.out.println("The book has added!");
                                }
                            }
                            else {
                                System.out.println("This function is only for admin!");
                            }
                        }
                        else if(option == 21) {
                            System.out.println("sss: " + userLogin);
                            command = "statusCheck";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            bufferedWriter.write(userLogin);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true")) {
                                System.out.println("To which user? Write userName: ");
                                tempVar1 = scanner.nextLine();
                                bufferedWriter.write(tempVar1); //tempvar1 with userName
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            } else {
                                System.out.println("This function is only for admin!");
                            }
                        }
                        else if(option == 12){
                            command = "showBooks";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            String response;
                            while (!(response = bufferedReader.readLine()).equalsIgnoreCase("END")) {
                                System.out.println("Books: " + response);
                            }
                        }
                        else if(option == 22) {
                            command = "statusCheck";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            bufferedWriter.write(userLogin);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true")) {
                                command = "delBook";
                                bufferedWriter.write(command);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Write down name of the book you want delete to: ");
                                tempVar1 = scanner.nextLine();
                                bufferedWriter.write(tempVar1);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Write down user, which has this book: ");
                                tempVar2 = scanner.nextLine();
                                bufferedWriter.write(tempVar2);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                msgFromServer = bufferedReader.readLine();
                                if (msgFromServer.equals("true")) {
                                    System.out.println("The book has deleted!");
                                }
                            }
                            else{
                                System.out.println("This function is only for admin!");
                            }
                        }

                        else if(option == 23) {
                            command = "statusCheck";
                            bufferedWriter.write(command);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            bufferedWriter.write(userLogin);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            msgFromServer = bufferedReader.readLine();
                            if (msgFromServer.equals("true")) {
                                command = "newAccount";
                                bufferedWriter.write(command);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Input login: ");
                                tempVar1 = scanner.nextLine();
                                bufferedWriter.write(tempVar1);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("Input password: ");
                                tempVar2 = scanner.nextLine();
                                bufferedWriter.write(tempVar2);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                System.out.println("User has added");
                            }
                            else{
                                System.out.println("This function is only for admin!");
                            }
                        }
                        else if(option == 0){
                            break;
                        }
                        else{
                            System.out.println("You choose unexisted option. Try again!");
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (socket != null)
                            socket.close();
                        if (inputStreamReader != null)
                            inputStreamReader.close();
                        if (outputStreamWriter != null)
                            outputStreamWriter.close();
                        if (bufferedReader != null)
                            bufferedReader.close();
                        if (bufferedWriter != null)
                            bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }