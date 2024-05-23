/*
 * @ {#} Handler.java   1.0     04/05/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package process;

import dao.BookDao;
import dao.impl.BookImpl;
import entity.Book;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   04/05/2024
 * @version:    1.0
 */
public class Handler implements Runnable{
    private Socket socket;
    private BookDao bookDao;

    public Handler(Socket socket) {
        this.socket = socket;
        bookDao = new BookImpl();
    }
    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            int choice = 0;
            while (true) {
                choice = dis.readInt();
                switch (choice) {
                    case 1:
                        String author = dis.readUTF();
                        int rating = dis.readInt();
                        List<Book> books = bookDao.listRatedBooks(author, rating);
                        oos.writeObject(books);
                        oos.flush();
                        break;
                    case 2:
                        Map<String, Long> map = bookDao.countBooksByAuthor();
                        oos.writeObject(map);
                        oos.flush();
                        break;
                    case 3:
                        String isbn = dis.readUTF();
                        String readerID = dis.readUTF();
                        int rating1 = dis.readInt();
                        String comment = dis.readUTF();
                        boolean result = bookDao.updateReviews(isbn, readerID, rating1, comment);
                        oos.writeBoolean(result);
                        break;
                    case 0:
                        socket.close();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
