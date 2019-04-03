package ua.pp.darknsoft.dao.implementation;

import org.springframework.stereotype.Repository;
import ua.pp.darknsoft.dao.interfaces.InterviewerDao;
import ua.pp.darknsoft.domain.entity.Interviewer;

@Repository
public class InterviewerDaoImpl extends GenericDaoImpl<Interviewer, Long> implements InterviewerDao {

    public InterviewerDaoImpl() {
        super(Interviewer.class);
    }
}
