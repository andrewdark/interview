package ua.pp.darknsoft.dao.implementations;

import ua.pp.darknsoft.dao.interfaces.InterviewerDao;
import ua.pp.darknsoft.domain.entity.Interviewer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *     This class is implementations class for {@link ua.pp.darknsoft.dao.interfaces.InterviewerDao}.
 * </p>
 *
 * @author <a href='mailto:antoxalanio@gmail.com'>Anton Lomakin</a>
 *
 * @since 1.0
 */

@Repository
public class InterviewerDaoImpl extends GenericDaoImpl<Interviewer, Long> implements InterviewerDao {

    public InterviewerDaoImpl() { super(Interviewer.class); }

    @Override
    public Interviewer findByEmail(String email) {
        Assert.notNull(email, "Email cannot be null");

        List<Interviewer> result = getEntityManager()
                .createQuery("SELECT i FROM "+ getClazz().getSimpleName() + " i where i.email = :email",
                        getClazz())
                .setParameter("email", email)
                .getResultList();

        if(!result.isEmpty()) return result.get(0);

        return null;
    }
}
