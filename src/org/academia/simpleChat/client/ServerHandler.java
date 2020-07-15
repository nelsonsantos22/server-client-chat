package org.academia.simpleChat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.spi.AbstractResourceBundleProvider;

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

            } catch (SocketException e){

                System.err.println("DISCONNECTED " + e.getMessage());

                //return;
                //e.printStackTrace();


            } catch (IOException e) {

                e.printStackTrace();

            }
    }
}
