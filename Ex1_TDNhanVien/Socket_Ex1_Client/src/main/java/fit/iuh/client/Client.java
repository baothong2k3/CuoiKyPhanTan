/**
 * @ (#) Client.java      4/24/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.iuh.client;

import entity.Candidate;
import entity.Position;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/24/2024
 */
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected to server");
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
                        Map<Candidate, Long> map = (Map<Candidate, Long>) ois.readObject();
                        map.entrySet().forEach(entry -> {
                            System.out.println(entry.getKey());
                            System.out.println("So cong ty tung lam: " + entry.getValue());
                        });
                    }
                    case 3 -> {
                        System.out.println("3. List Candidates With Longest Working");
                        Map<Candidate, Position> map = (Map<Candidate, Position>) ois.readObject();
                        map.entrySet().forEach(entry -> {
                            System.out.println("Ung vien: " + entry.getKey().getFullName() + "\n Vi tri:  " + entry.getValue().getName());
                        });
                    }
                    case 4 -> {
                        System.out.println("4. Add Candidate");
                        scanner = new Scanner(System.in);

                        System.out.println("Enter candidate id: ");
                        String candidate_id = scanner.nextLine();
                        out.writeUTF(candidate_id);
                        out.flush();

                        System.out.println("Enter full_name candidate: ");
                        String full_name = scanner.nextLine();
                        out.writeUTF(full_name);
                        out.flush();

                        System.out.println("Enter year of yearOfBirth: ");
                        int yearOfBirth = scanner.nextInt();
                        out.writeInt(yearOfBirth);
                        out.flush();

                        scanner = new Scanner(System.in);
                        System.out.println("Enter gender candidate: ");
                        String gender = scanner.nextLine();
                        out.writeUTF(gender);
                        out.flush();

                        System.out.println("Enter email candidate: ");
                        String email = scanner.nextLine();
                        out.writeUTF(email);
                        out.flush();

                        System.out.println("Enter phone candidate: ");
                        String phone = scanner.nextLine();
                        out.writeUTF(phone);
                        out.flush();

                        System.out.println("Enter description candidate: ");
                        String description = scanner.nextLine();
                        out.writeUTF(description);
                        out.flush();

                        System.out.println("Enter position job id : ");
                        String position_id = scanner.next();
                        out.writeUTF(position_id);
                        out.flush();

                        boolean check = in.readBoolean();
                        if (check) {
                            System.out.println("Add candidate success");
                        } else {
                            System.out.println("Add candidate fail");
                        }
                    }
                    case 5 -> {
                        System.out.println("5. List Years Of Experience By Position");
                        System.out.println("Enter Candidate id: ");
                        String candidateId = scanner.next();
                        out.writeUTF(candidateId);
                        out.flush();
                        Map<Position, Integer> map = (Map<Position, Integer>) ois.readObject();
                        System.out.println("List Years Of Experience By Position Of Candidate: " + candidateId);
                        map.entrySet().forEach(entry -> {
                            System.out.println("Vi tri: " + entry.getKey().getName() + "\n So nam kinh nghiem: " + entry.getValue());
                        });
                    }
                    case 6 -> {
                        System.out.println("6. List Candidates And Certificates");
                        String thongBaoServer = in.readUTF();
                        System.out.println(thongBaoServer);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
