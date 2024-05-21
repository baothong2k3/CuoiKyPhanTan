/**
 * @ (#) Handler.java      4/24/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package process;

import dao.ICandidateDao;
import dao.IExperience;
import dao.IPositionsDao;
import dao.impl.CandidateDaoImpl;
import dao.impl.ExperienceDaoImpl;
import dao.impl.PositionsDaoImpl;
import entity.Candidate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/24/2024
 */
public class Handler implements Runnable {
    private Socket socket;
    private IExperience iExperience;
    private ICandidateDao iCandidateDao;
    private IPositionsDao iPositionsDao;
    EntityManager em = null;
    EntityTransaction et = null;
    public Handler(Socket socket) {
        this.socket = socket;
        iExperience = new ExperienceDaoImpl();
        iCandidateDao = new CandidateDaoImpl();
        iPositionsDao = new PositionsDaoImpl();
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                int choose = in.readInt();
                System.out.println("Client choose: " + choose);
                switch (choose) {
                    case 0:
                        System.out.println("Client choose Exit");
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("Client choose List positions");
                        String name = in.readUTF();
                        double salaryFrom = in.readDouble();
                        double salaryTo = in.readDouble();
                        List<Position> list = iPositionsDao.listPositions(name, salaryFrom, salaryTo);
                        oos.writeObject(list);
                        oos.flush();
                        break;
                    case 2:
                        System.out.println("Client choose List Candidates By Companies");
                        Map<Candidate, Long> map = iCandidateDao.listCadidatesByCompanies();
                        oos.writeObject(map);
                        oos.flush();
                        break;
                    case 3:
                        System.out.println("Client choose List Candidates With Longest Working");
                        Map<Candidate, Position> map1 = iExperience.listCandidatesWithLongestWorking();
                        oos.writeObject(map1);
                        oos.flush();
                        break;
                    case 4:
                        System.out.println("Client choose Add Candidate");

                        String candidate_id = in.readUTF();
                        String fullName = in.readUTF();
                        int yearOfBirth = in.readInt();
                        String gender = in.readUTF();
                        String email = in.readUTF();
                        String phone = in.readUTF();
                        String description = in.readUTF();

                        String position_id = in.readUTF();

                        Candidate candidate = new Candidate(candidate_id, fullName, yearOfBirth, gender, email, phone, description);
                        Position position = em.find(Position.class, position_id);
                        candidate.setPosition(position);

                        boolean check =  iCandidateDao.addCandidate(candidate);
                        out.writeBoolean(check);
                        out.flush();
                        break;
                    case 5:
                        System.out.println("Client choose List Years Of Experience By Position");
                        String candidateId = in.readUTF();
                        Map<Position, Integer> map2 = iExperience.listYearsOfExperienceByPosition(candidateId);
                        oos.writeObject(map2);
                        oos.flush();
                        break;
                    case 6:
                        System.out.println("Client choose List Candidates And Certificates");
                        String thongBao = "He thong dang trong qua trinh hoan thien chuc nang nay";
                        out.writeUTF(thongBao);
                        out.flush();
                        break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
