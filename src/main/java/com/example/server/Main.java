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



public class Main{

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
                    String tempVar1, tempVar2,tempVar3,privilege, msgOut, tempVar4;





                    while (true) {
                        tempVar1 = bufferedReader.readLine();
                        if(tempVar1.equals("Login")){
                            tempVar1 = bufferedReader.readLine();
                            tempVar2 = bufferedReader.readLine();
                            msgOut = Boolean.toString(autorization.autorizate(tempVar1, tempVar2));
                            bufferedWriter.write(msgOut);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        else if(tempVar1.equals("Registration")){
                            tempVar1 = bufferedReader.readLine();
                            tempVar2 = bufferedReader.readLine();
                            msgOut = Boolean.toString(autorization.newRegistration(tempVar1, tempVar2));
                            bufferedWriter.write(msgOut);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                        else if(tempVar1.equals("statusCheck")){
                            tempVar1 = bufferedReader.readLine();
                            msgOut = Boolean.toString(autorization.checkStatus(tempVar1));
                            bufferedWriter.write(msgOut);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                            else if(tempVar1.equals("newBook")){
                                tempVar4 = bufferedReader.readLine();
                                tempVar1 = bufferedReader.readLine();
                                tempVar2 = bufferedReader.readLine();
                                tempVar3 = bufferedReader.readLine();
                                msgOut = Boolean.toString(libraryManager.newBook(tempVar1, tempVar2, tempVar3,tempVar4));
                                bufferedWriter.write(msgOut);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }
                            else if(tempVar1.equals("newAccount")){
                                tempVar1 = bufferedReader.readLine();
                                tempVar2 = bufferedReader.readLine();
                                msgOut = Boolean.toString(autorization.newRegistration(tempVar1, tempVar2));
                                bufferedWriter.write(msgOut);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }
                            else if(tempVar1.equals("showBooks")) {
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
                            else if(tempVar1.equals("delBook")){
                                tempVar1 = bufferedReader.readLine();
                                tempVar2 = bufferedReader.readLine();
                                msgOut = Boolean.toString(libraryManager.delBook(tempVar1, tempVar2));
                                bufferedWriter.write(msgOut);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }

                        //System.out.println("Client2: " + tempVar2);

                        if (tempVar1.equalsIgnoreCase("Bye")) {
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