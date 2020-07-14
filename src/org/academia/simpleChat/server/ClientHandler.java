package org.academia.simpleChat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class ClientHandler implements Runnable{

    Socket clientSocket;
    LinkedList<ClientHandler> list;
    PrintWriter out;



    public ClientHandler(Socket clientSocket, LinkedList list){
        this.clientSocket = clientSocket;
        this.list = list;


    }

    public void handleClient(Socket clientSocket){

        try {


            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messageIn;

            System.out.println("=== " + Thread.currentThread().getName() + " has connected ===");

            while (true) {

                messageIn = in.readLine();
                System.out.println("this message " + messageIn);

                if (messageIn == null || messageIn.equals("/quit")) {
                    System.out.println("=== " + Thread.currentThread().getName() + " disconnected ===");
                    in.close();
                    clientSocket.close();
                    break;
                }
                System.out.println(list.size());

                broadcast(messageIn);
                System.out.println(Thread.currentThread().getName() + ": " + messageIn);

            }
        } catch (IOException e){

            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        handleClient(clientSocket);
    }

    public void broadcast(String response){

        for (ClientHandler client : list){
            client.out.println(response);
        }
    }

}
