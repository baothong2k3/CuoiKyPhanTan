/*
 * @ (#) CandidateDAO.java    1.0    17/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 17/05/2024
 * @version: 1.0
 */

import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CandidateDAO {
    public Map<Candidate, Long> listCadidatesByCompanies();
    public  Map<Candidate, Position> listCandidatesWithLongestWorking ();
    public boolean addCandidate(Candidate Candidate);
    public  Map<Candidate, Set<Certificate>>  listCadidatesAndCertificates();
}
