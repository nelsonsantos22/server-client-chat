package org.academia.simpleChat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private Socket clientSocket;
    private String chatMessage;

    public ServerHandler(Socket clientSocket){
        this.clientSocket = clientSocket;

    }


    @Override
    public void run() {

            try {

                BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while (true) {

                    chatMessage = serverIn.readLine();
                    System.out.println(chatMessage);

                }
            } catch (IOException e) {

                e.printStackTrace();

            }
    }
}
