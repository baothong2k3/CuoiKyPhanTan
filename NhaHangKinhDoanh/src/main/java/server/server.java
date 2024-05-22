/*
 * @ (#) server.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package server;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(8080);
            System.out.println("Server is running");
            while (true) {
                Socket socket1 = socket.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket1.getInetAddress().getHostAddress());
                System.out.println("Client Port: " + socket1.getPort());
                handler handler = new handler(socket1);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
