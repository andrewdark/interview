package ua.pp.darknsoft.dao.implementation;

import ua.pp.darknsoft.dao.interfaces.CandidateDao;
import ua.pp.darknsoft.domain.entity.Candidate;

public class CandidateDaoImpl extends GenericDaoImpl<Candidate, Long> implements CandidateDao {

    protected CandidateDaoImpl(Class<Candidate> clazz) {
        super(clazz);
    }
}
