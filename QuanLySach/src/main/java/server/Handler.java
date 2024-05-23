/*
 * @ (#) Handler.java    1.0    23/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package server;/*
 * @description:
 * @author: Bao Thong
 * @date: 23/05/2024
 * @version: 1.0
 */

import dao.BookDao;
import dao.Impl.BookImpl;
import dao.Impl.ReviewImpl;
import dao.ReviewDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;
    private BookDao bookDao;
    private ReviewDao reviewDao;
    private EntityManager em;

    public Handler(Socket socket) {
        this.socket = socket;
        bookDao = new BookImpl();
        reviewDao = new ReviewImpl();
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                int choice = dis.readInt();
                switch (choice) {
                    case 0: {
                        System.exit(0);
                        return;
                    }
                    case 1: {
                        String author = dis.readUTF();
                        int rating = dis.readInt();
                        dos.writeUTF(bookDao.listRatedBooks(author, rating).toString());
                        break;
                    }
                    case 2: {
                       oos.writeObject(bookDao.countBooksByAuthor());
                        break;
                    }
                    case 3: {
                        String ISBN = dis.readUTF();
                        String readerID = dis.readUTF();
                        int rating = dis.readInt();
                        String comment = dis.readUTF();
                        dos.writeBoolean(reviewDao.updateReviews(ISBN, readerID, rating, comment));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
