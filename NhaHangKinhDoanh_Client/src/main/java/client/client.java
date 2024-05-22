/*
 * @ (#) client.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package client;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import entity.Food;
import entity.Item;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connect to server");
            while (true) {
                System.out.println("0. Out");
                System.out.println("1. Add food");
                System.out.println("2. List Item by supplierName");
                System.out.println("3. List Food and Cost");
                System.out.println("Choice: ");
                int choice = scanner.nextInt();
                out.writeInt(choice);
                out.flush();
                switch (choice) {
                    case 0: {
                        System.exit(0);
                        return;
                    }
                    case 1: {
                        System.out.println("1. Add food");
                        System.out.println("Enter id: ");
                        String id = scanner.next();
                        out.writeUTF(id);
                        System.out.println("Enter name: ");
                        String name = scanner.nextLine();
                        out.writeUTF(name);
                        System.out.println("Enter price: ");
                        double price = scanner.nextDouble();
                        out.writeDouble(price);
                        System.out.println("Enter description: ");
                        String description = scanner.nextLine();
                        out.writeUTF(description);
                        System.out.println("Enter onSpecial: ");
                        boolean onSpecial = scanner.nextBoolean();
                        out.writeBoolean(onSpecial);
                        System.out.println("Enter type: ");
                        String type = scanner.nextLine();
                        out.writeUTF(type);
                        System.out.println("Enter preparationTime: ");
                        int preparationTime = scanner.nextInt();
                        out.writeInt(preparationTime);

                        boolean check = in.readBoolean();
                        if (check) {
                            System.out.println("Add food success");
                        } else {
                            System.out.println("Add food fail");
                        }
                        break;
                    }
                    case 2:{
                        System.out.println("2. List Item by supplierName");
                        System.out.println("SupplierName: ");
                        scanner.nextLine();
                        String supplierName = scanner.nextLine();
                        out.writeUTF(supplierName);
                        List<Item> list = (List<Item>) ois.readObject();
                        list.forEach(System.out::println);
                        break;
                    }
                    case 3:{
                        System.out.println("3. List food and cost");
                        Map<Food, Double> list = (Map<Food, Double>) ois.readObject();
                        list.forEach((k, v) -> System.out.println(k + " - " + v));
                        break;
                    }
                }
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
