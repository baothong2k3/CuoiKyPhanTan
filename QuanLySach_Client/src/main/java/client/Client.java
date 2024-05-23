/*
 * @ (#) Client.java    1.0    23/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package client;/*
 * @description:
 * @author: Bao Thong
 * @date: 23/05/2024
 * @version: 1.0
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ){
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("0. Out");
                System.out.println("1. List rated books");
                System.out.println("2. Count books by author");
                System.out.println("3. Update review");
                System.out.println("Enter your choice: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("That's not a valid option! Please enter an integer.");
                    scanner.next();
                }
                int choice = scanner.nextInt();
                dos.writeInt(choice);
                dos.flush();
                switch (choice){
                    case 0:
                        System.out.println("Exit");
                        System.exit(0);
                        return;
                    case 1:
                        System.out.println("Enter author: ");
                        String author = scanner.next();
                        System.out.println("Enter rating: ");
                        int rating = scanner.nextInt();
                        dos.writeUTF(author);
                        dos.writeInt(rating);
                        System.out.println(dis.readUTF());
                        break;
                    case 2:
                        Map<String, Long> authorBookCounts = (Map<String, Long>) ois.readObject();
                        for (Map.Entry<String, Long> entry : authorBookCounts.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                        break;
                    case 3:
                        System.out.println("Enter ISBN: ");
                        String ISBN = scanner.next();
                        System.out.println("Enter readerID: ");
                        scanner.nextLine();
                        String readerID = scanner.nextLine();
                        System.out.println("Enter rating: ");
                        int rating1 = scanner.nextInt();
                        System.out.println("Enter review: ");
                        String review = scanner.next();
                        dos.writeUTF(ISBN);
                        dos.writeUTF(readerID);
                        dos.writeInt(rating1);
                        dos.writeUTF(review);
                        dos.flush();
                        boolean check = dis.readBoolean();
                        if (check){
                            System.out.println("Update review successfully");
                        }else {
                            System.out.println("Update review failed");
                        }
                        break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
