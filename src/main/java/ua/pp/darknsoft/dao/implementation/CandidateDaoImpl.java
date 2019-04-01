package ua.pp.darknsoft.dao.implementation;

import org.springframework.stereotype.Repository;
import ua.pp.darknsoft.dao.interfaces.CandidateDao;
import ua.pp.darknsoft.domain.entity.Candidate;

@Repository
public class CandidateDaoImpl extends GenericDaoImpl<Candidate, Long> implements CandidateDao {

    public CandidateDaoImpl() {
        super(Candidate.class);
    }
}
