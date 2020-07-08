package org.academia.simpleChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {

    public static void main(String[] args){

        int portNumber = 9990;

        try{

            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for new client to establish connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection established to: " + "address = " + clientSocket.getLocalSocketAddress() + " localport = " + clientSocket.getPort());

            //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (clientSocket.isBound()) {

                String messageIn = in.readLine();

                if (messageIn == null || messageIn.equals("/quit")) {
                    System.out.println("Closing server, goodbye!");
                    break;
                }

                System.out.println("Client message: " + messageIn);
            }

            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException exception) {

            exception.printStackTrace();

        }
    }
}
