package ua.pp.darknsoft.dao.implementation;

import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.entity.Interview;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class InterviewDaoImpl extends GenericDaoImpl<Interview, Long> implements InterviewDao {

    public InterviewDaoImpl() {
        super(Interview.class);
    }

    @Override
    public List<Interview> getFilteredInterviews(FilterInterviewBuilder filterInterviewBuilder) {
        Assert.notNull(filterInterviewBuilder, "BAD param. Interview is not null");

        return getEntityManager()
                .createQuery("SELECT i FROM Interview i WHERE " +
                        "(i.position = :position  or :position IS NULL) " +
                        "AND (i.date = :date  or CAST(:date AS date) IS NULL) " +
                        "AND (i.status = :status  or :status IS NULL) " +
                        "AND (i.candidate.firstName = :firstname or :firstname IS NULL) " +
                        "AND (i.candidate.lastName = :lastname or :lastname IS NULL) " +
                        "AND (i.candidate.email = :email or :email IS NULL)", Interview.class)
                .setParameter("position", filterInterviewBuilder.getPosition())
                .setParameter("date", filterInterviewBuilder.getDate())
                .setParameter("status", filterInterviewBuilder.getStatus())
                .setParameter("firstname", filterInterviewBuilder.getFirstName())
                .setParameter("lastname", filterInterviewBuilder.getLastName())
                .setParameter("email", filterInterviewBuilder.getEmail())
                .getResultList();
    }

    @Override
    public boolean isExist(Interview interview) {
        Assert.notNull(interview, "BAD param. Interview should not be null");
        Assert.notNull(interview.getCandidate(), "BAD param. Candidate should not be null");
        try {
            getEntityManager().createQuery("SELECT i FROM " + Interview.class.getSimpleName() + " i WHERE  " +
                    "(i.position = :position) " +
                    "AND (i.date = :date) " +
                    "AND (i.candidate.firstName = :firstname) " +
                    "AND (i.candidate.lastName = :lastname) " +
                    "AND (i.candidate.email = :email)", Interview.class)
                    .setParameter("position", interview.getPosition())
                    .setParameter("date", interview.getDate())
                    .setParameter("firstname", interview.getCandidate().getFirstName())
                    .setParameter("lastname", interview.getCandidate().getLastName())
                    .setParameter("email", interview.getCandidate().getEmail())
                    .getSingleResult();
        } catch (NoResultException nre) {
            return false;
        } catch (NonUniqueResultException nre) {
            return true; // or false ?? TODO
        }
        return true;
    }
}
