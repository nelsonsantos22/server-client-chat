package org.academia.simpleChat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

    public static void main(String[] args){

        String hostName = "localhost";
        int serverPort = 9990;

        try {

            Socket clientSocket = new Socket(InetAddress.getByName(hostName), serverPort);
            System.out.println("Connection established with " + InetAddress.getByName(hostName) + " on port " + serverPort);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            System.out.print("Your message:");
            String messageOut = "";

            while(!messageOut.equals("/quit")) {

                messageOut = scanner.nextLine();
                out.write(messageOut + "\n");
                out.flush();
                //out.close();

            }

            clientSocket.close();

        } catch (IOException exception) {

            exception.printStackTrace();

        } finally {



        }

    }
}
