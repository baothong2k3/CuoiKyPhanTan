/*
 * @ (#) handler.java    1.0    22/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package server;/*
 * @description:
 * @author: Bao Thong
 * @date: 22/05/2024
 * @version: 1.0
 */

import dao.FoodDAO;
import dao.Impl.FoodImpl;
import dao.Impl.ItemImpl;
import dao.ItemDAO;
import entity.Food;
import entity.Item;
import entity.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class handler implements Runnable {
    private Socket socket;
    private FoodDAO foodDAO;
    private ItemDAO itemDAO;
    private EntityManager em;

    public handler(Socket socket) {
        this.socket = socket;
        foodDAO = new FoodImpl();
        itemDAO = new ItemImpl();
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                int choice = dis.readInt();
                switch (choice) {
                    case 0: {
                        System.out.println("Exit");
                        break;
                    }
                    case 1: {
                        System.out.println("1. Add food");
                        String id = dis.readUTF();
                        String name = dis.readUTF();
                        double price = dis.readDouble();
                        String description = dis.readUTF();
                        boolean onSpecial = dis.readBoolean();
                        String type = dis.readUTF();
                        int preparationTime = dis.readInt();
                        int servingTime = dis.readInt();
                        Food f = new Food(id, name, price, description, onSpecial, Type.valueOf(type), preparationTime, servingTime);
                        boolean check = foodDAO.addFood(f);
                        dos.writeBoolean(check);
                        dos.flush();
                        break;
                    }
                    case 2: {
                        System.out.println("2. List Item by supplierName");
                        String supplierName = dis.readUTF();
                        List<Item> list = itemDAO.listItems(supplierName);
                        oos.writeObject(list);
                        oos.flush();
                        break;
                    }
                    case 3: {
                        System.out.println("3. List Food and Cost");
                        Map<Food, Double> map = foodDAO.listFoodAndCost();
                        oos.writeObject(map);
                        oos.flush();
                        break;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
