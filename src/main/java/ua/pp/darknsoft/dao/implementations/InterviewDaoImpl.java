package ua.pp.darknsoft.dao.implementations;

import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.dto.InterviewFilterDto;
import ua.pp.darknsoft.domain.entity.Interview;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * This class is implementations class for {@link ua.pp.darknsoft.dao.interfaces.InterviewDao}.
 * </p>
 *
 * @author <a href='mailto:antoxalanio@gmail.com'>Anton Lomakin</a>
 * @author <a href='mailto:samsonov.a@ukr.net'>Samsonov Andrew</a>
 * @since 1.0
 */

@Repository
public class InterviewDaoImpl extends GenericDaoImpl<Interview, Long> implements InterviewDao {

    public InterviewDaoImpl() {
        super(Interview.class);
    }

    @Override
    public List<Interview> getInterviewWithFilter(InterviewFilterDto filter) {
        Assert.notNull(filter, "BAD param. Interview should not be null");

        return getEntityManager()
                .createQuery("SELECT i FROM Interview i WHERE " +
                        "(i.position = :position  or :position IS NULL) " +
                        "AND (i.date = :date  or CAST(:date AS date) IS NULL) " +
                        "AND (i.status = :status  or :status IS NULL) " +
                        "AND (i.candidate.firstName = :firstname or :firstname IS NULL) " +
                        "AND (i.candidate.lastName = :lastname or :lastname IS NULL) " +
                        "AND (i.candidate.email = :email or :email IS NULL)", Interview.class)
                .setParameter("position", filter.getPosition())
                .setParameter("date", filter.getDate())
                .setParameter("status", filter.getStatus())
                .setParameter("firstname", filter.getFirstName())
                .setParameter("lastname", filter.getLastName())
                .setParameter("email", filter.getEmail())
                .getResultList();
    }
}
