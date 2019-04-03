package ua.pp.darknsoft.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.entity.Candidate;
import ua.pp.darknsoft.domain.entity.Interview;

import java.util.List;

@Repository
@Transactional
public class InterviewDaoImpl extends GenericDaoImpl<Interview, Long> implements InterviewDao {

    public InterviewDaoImpl() {
        super(Interview.class);
    }


    @Override
    public List<Interview> getFilteredInterviews(Interview interview) {
        Assert.notNull(interview, "BAD param. Interview is not null");

        if (interview.getCandidate() == null) {
            interview.setCandidate(new Candidate());
        }

        List<Interview> interviewList = getEntityManager()
                .createQuery("SELECT i FROM Interview i WHERE " +
                        "(i.position = :position  or :position IS NULL) " +
                        "AND (i.date = :date  or CAST(:date AS date) IS NULL) " +
                        "AND (i.status = :status  or :status IS NULL) " +
                        "AND (i.candidate.firstName = :firstname or :firstname IS NULL) " +
                        "AND (i.candidate.lastName = :lastname or :lastname IS NULL) " +
                        "AND (i.candidate.email = :email or :email IS NULL)", Interview.class)
                .setParameter("position", interview.getPosition())
                .setParameter("date", interview.getDate())
                .setParameter("status", interview.getStatus())
                .setParameter("firstname", interview.getCandidate().getFirstName())
                .setParameter("lastname", interview.getCandidate().getLastName())
                .setParameter("email", interview.getCandidate().getEmail())
                .getResultList();
        return interviewList;
    }
}
