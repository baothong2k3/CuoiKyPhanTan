/*
 * @ (#) Server.java    1.0    23/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package server;/*
 * @description:
 * @author: Bao Thong
 * @date: 23/05/2024
 * @version: 1.0
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server is running on port 1234");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                System.out.println("Client address: " + socket.getInetAddress().getHostAddress());
                System.out.println("Client port: " + socket.getPort());
                Handler handler = new Handler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
