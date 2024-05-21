/*
 * @ (#) Handler.java    1.0    21/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package Server;/*
 * @description:
 * @author: Bao Thong
 * @date: 21/05/2024
 * @version: 1.0
 */

import dao.CandidateDAO;
import dao.Impl.CandidateImpl;
import dao.Impl.PositionImpl;
import dao.PositionDAO;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Handler implements Runnable {
    private Socket socket;
    private CandidateDAO candidateDAO;
    private PositionDAO positionDAO;
    private EntityManager em = null;

    public Handler(Socket socket) {
        this.socket = socket;
        candidateDAO = new CandidateImpl();
        positionDAO = new PositionImpl();
        em = Persistence.createEntityManagerFactory("OnTapJPA_SocKet_sql").createEntityManager();
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                int choice = in.readInt();
                switch (choice) {
                    case 0:
                        System.out.println("Exit");
                        break;
                    case 1:
                        System.out.println("1. List positions (name, salary from, salary to)");
                        String name = in.readUTF();
                        double salaryFrom = in.readDouble();
                        double salaryTo = in.readDouble();
                        List<Position> positions = positionDAO.listPositions(name, salaryFrom, salaryTo);
                        oos.writeObject(positions);
                        oos.flush();
                        break;
                    case 2:
                        System.out.println("2. List Candidates By Companies");
                        Map<Candidate, Long> rs = candidateDAO.listCadidatesByCompanies();
                        oos.writeObject(rs);
                        oos.flush();
                        break;
                    case 3:
                        System.out.println("3. List Candidates With Longest Working");
                        Map<Candidate, Position> candidates = candidateDAO.listCandidatesWithLongestWorking();
                        oos.writeObject(candidates);
                        oos.flush();
                        break;
                    case 4:
                        System.out.println("4. Add Candidate");
                        String id = in.readUTF();
                        String fullName = in.readUTF();
                        int yearOfBirth = in.readInt();
                        String gender = in.readUTF();
                        String phone = in.readUTF();
                        String email = in.readUTF();
                        String description = in.readUTF();

                        String positionId = in.readUTF();

                        Candidate candidate = new Candidate(id, fullName, yearOfBirth, gender, email, phone, description);
                        Position p = em.find(Position.class, positionId);
                        candidate.setPosition(p);
                        boolean check = candidateDAO.addCandidate(candidate);
                        out.writeBoolean(check);
                        out.flush();
                        break;
                    case 5:
                        System.out.println("5. List year of experience by position");
                        String candidate_id = in.readUTF();
                        Map<Position, Integer> rs1 = positionDAO.listYearsOfExperienceByPosition(candidate_id);
                        oos.writeObject(rs1);
                        oos.flush();
                        break;
                    case 6:
                        System.out.println("6. list candidate and certificate");
                        Map<Candidate, Set<Certificate>> result = candidateDAO.listCadidatesAndCertificates();
                        oos.writeObject(result);
                        oos.flush();
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
