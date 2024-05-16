/*
 * @ (#) CadidateImpl.java    1.0    17/05/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao.Impl;/*
 * @description:
 * @author: Bao Thong
 * @date: 17/05/2024
 * @version: 1.0
 */

import dao.CandidateDAO;
import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.util.Map;
import java.util.Set;

public class CandidateImpl implements CandidateDAO{
    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        return null;
    }

    @Override
    public Map<Candidate, Position> listCandidatesWithLongestWorking() {
        return null;
    }

    @Override
    public boolean addCandidate(Candidate Candidate) {
        return false;
    }

    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        return null;
    }
}
