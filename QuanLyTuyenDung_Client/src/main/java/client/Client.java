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

import entity.Candidate;
import entity.Position;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
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
            while (true) {
                System.out.println("""
                        0. Exit \s
                        1. List positions\s
                        2. List Candidates By Companies\s
                        3. List Candidates With Longest Working\s
                        4. Add Candidate\s
                        5. List Years Of Experience By Position\s
                        6. List Candidates And Certificates""");
                System.out.print("Choose: ");
                int choice = scanner.nextInt();
                out.writeInt(choice);
                out.flush();
                switch (choice) {
                    case 0 -> {
                        System.out.println("Exit");
                        System.exit(0);
                        return;
                    }
                    case 1 -> {
                        System.out.println("1. List positions");
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
                    }
                    case 2 -> {
                        System.out.println("2. List Candidates By Companies");
                        Map<Candidate, Long> companies = (Map<Candidate, Long>) ois.readObject();
                        for (Map.Entry<Candidate, Long> entry : companies.entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        }
                    }
                    case 3 -> {
                        System.out.println("3. List Candidates With Longest Working");
                        Map<Candidate, Position> result = (Map<Candidate, Position>) ois.readObject();
                        for (Map.Entry<Candidate, Position> entry : result.entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        }
                    }
                    case 4 -> {
                        System.out.println("4.Add Candidate");
                        System.out.println("4.1 Enter id: ");
                        String id = scanner.next();
                        out.writeUTF(id);

                        scanner.nextLine();
                        System.out.println("4.2 Enter full name: ");
                        String fullName = scanner.nextLine();
                        out.writeUTF(fullName);

                        System.out.println("4.3 Enter year of birth: ");
                        int yearOfBirth = scanner.nextInt();
                        out.writeInt(yearOfBirth);

                        scanner.nextLine();
                        System.out.println("4.4 Enter gender: ");
                        String gender = scanner.nextLine();
                        out.writeUTF(gender);

                        System.out.println("4.5 Enter phone: ");
                        String phone = scanner.nextLine();
                        out.writeUTF(phone);

                        System.out.println("4.6 Enter email: ");
                        String email = scanner.nextLine();
                        out.writeUTF(email);

                        System.out.println("4.7 Enter description: ");
                        String description = scanner.nextLine();
                        out.writeUTF(description);

                        System.out.println("4.8 Enter position id: ");
                        String positionId = scanner.nextLine();
                        out.writeUTF(positionId);
                        out.flush();

                        boolean check = in.readBoolean();
                        if (check) {
                            System.out.println("Add candidate success");
                        } else {
                            System.out.println("Add candidate fail");
                        }
                    }
                    case 5 ->{
                        System.out.println("5. List year of experience by position");
                        System.out.println("5.1 Enter candidate id: ");
                        String candidate_id = scanner.next();
                        out.writeUTF(candidate_id);
                        out.flush();

                        Map<Position, Integer> rs1 = (Map<Position, Integer>) ois.readObject();
                        for (Map.Entry<Position, Integer> entry : rs1.entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        }
                    }
                    case 6 ->{
                        System.out.println("6. list candidate and certificate");
                        Map<Candidate, List<String>> result = (Map<Candidate, List<String>>) ois.readObject();
                        for (Map.Entry<Candidate, List<String>> entry : result.entrySet()) {
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        }
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
