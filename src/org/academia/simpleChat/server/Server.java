package org.academia.simpleChat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    Socket clientSocket;
    ExecutorService fixedPool = Executors.newFixedThreadPool(5);
    LinkedList<ClientHandler> list = new LinkedList<>();


    public Server(){
        startServer();
    }

    public void startServer(){

        int portNumber = 8081;

        try{

            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for new client to establish connection...");


            while (true) {

                clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(clientSocket, list);

                fixedPool.execute(clientHandler);
                list.add(clientHandler);

            }

        } catch (SocketException e){

            System.err.println("USER FORCED OUT");

        } catch (IOException exception) {

            exception.printStackTrace();

        }
    }

}
