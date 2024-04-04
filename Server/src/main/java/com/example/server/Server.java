package com.example.server;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;



public class Server{

        public static void main(String[] args) throws IOException {

            Socket socket = null;
            InputStreamReader inputStreamReader = null;
            OutputStreamWriter outputStreamWriter = null;
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(1234);

            while(true) {
                try {
                    socket = serverSocket.accept();
                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    bufferedWriter = new BufferedWriter(outputStreamWriter);

                    Autorization autorization = new Autorization();
                    LibraryManager libraryManager = new LibraryManager();
                    String command, msgOut;





                    while (true) {
                        command = bufferedReader.readLine();
                        if(command.equals("Login")){
                            String login = bufferedReader.readLine();
                            String password = bufferedReader.readLine();
                            msgOut = Boolean.toString(autorization.autorizate(login, password));
                            bufferedWriter.write(msgOut);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        else if(command.equals("Registration")){
                            String login = bufferedReader.readLine();
                            String password = bufferedReader.readLine();
                            msgOut = Boolean.toString(autorization.newRegistration(login, password));
                            bufferedWriter.write(msgOut);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        else if(command.equals("statusCheck")){
                            String userName = bufferedReader.readLine();
                            msgOut = Boolean.toString(autorization.checkStatus(userName));
                            bufferedWriter.write(msgOut);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                            else if(command.equals("newBook")){
                               String author = bufferedReader.readLine();
                                String name = bufferedReader.readLine();
                                String genre = bufferedReader.readLine();
                                String owner = bufferedReader.readLine();
                                msgOut = Boolean.toString(libraryManager.newBook(author, name, genre, owner));
                                bufferedWriter.write(msgOut);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }
                            else if(command.equals("newAccount")){
                                String login = bufferedReader.readLine();
                                String password = bufferedReader.readLine();
                                msgOut = Boolean.toString(autorization.newRegistration(login, password));
                                bufferedWriter.write(msgOut);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }
                            else if(command.equals("showBooks")) {
                                List<String> books = libraryManager.showBooks();
                                for (String book : books) {
                                    System.out.println("Book: " + book);
                                    bufferedWriter.write(book);
                                    bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                }
                                bufferedWriter.write("END");
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }
                            else if(command.equals("delBook")){
                                String name = bufferedReader.readLine();
                                String owner = bufferedReader.readLine();
                                msgOut = Boolean.toString(libraryManager.delBook(name, owner));
                                bufferedWriter.write(msgOut);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }

                        //System.out.println("Client2: " + tempVar2);

                        if (command.equalsIgnoreCase("Bye")) {
                            break;
                        }

                    }


                    socket.close();
                    inputStreamReader.close();
                    outputStreamWriter.close();
                    bufferedWriter.close();
                    bufferedReader.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


}
