package dao;

import entity.Candidate;
import entity.Certificate;

import java.util.Map;
import java.util.Set;

public interface ICandidateDao {
    public Map<Candidate,Long> listCadidatesByCompanies();
    public boolean addCandidate(Candidate candidate);
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}
