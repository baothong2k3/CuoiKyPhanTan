/*
 * @ (#) Client.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package client;/*
 * @description:
 * @author: Bao Thong
 * @date: 21/05/2024
 * @version: 1.0
 */

import entity.Position;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connect to server");
            int choice = scanner.nextInt();
            out.writeInt(choice);
            out.flush();
            switch (choice) {
                case 1:
                    System.out.println("1.1 Enter name position: ");
                    String name = scanner.next();
                    out.writeUTF(name);

                    System.out.println("1.2 Enter salary from: ");
                    double salaryFrom = scanner.nextDouble();
                    out.writeDouble(salaryFrom);

                    System.out.println("1.3 Enter salary to: ");
                    double salaryTo = scanner.nextDouble();
                    out.writeDouble(salaryTo);
                    out.flush();

                    List<Position> positions = (List<Position>) ois.readObject();
                    positions.forEach(System.out::println);
                    break;
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
