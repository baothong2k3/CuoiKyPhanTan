/*
 * @ {#} Server.java   1.0     04/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package server;

import process.Handler;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   04/05/2024
 * @version:    1.0
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket.getInetAddress().getHostAddress());
                System.out.println("Client Port: " + socket.getPort());

                Handler handler = new Handler(socket);
                Thread thread = new Thread(handler);
                thread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
