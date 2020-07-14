package org.academia.simpleChat.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientLauncher {


    public static void main(String[] args){

        String hostName = "localhost";
        int serverPort = 8081;

        try {

            Socket clientSocket = new Socket(InetAddress.getByName(hostName), serverPort);
            System.out.println("Connection established with " + InetAddress.getByName(hostName) + " on port " + serverPort);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String messageOut = "";
            String messageIn = "";

            ServerHandler serverHandler = new ServerHandler(clientSocket);

            while(!messageOut.equals("/quit")) {

                new Thread(serverHandler).start();

                messageIn = in.readLine();
                System.out.println(messageIn);

                //serverHandler.run();

                out.write(messageIn + "\n");
                out.flush();

            }

            clientSocket.close();

        } catch (IOException exception) {

            exception.printStackTrace();

        } finally {



        }

    }
}
