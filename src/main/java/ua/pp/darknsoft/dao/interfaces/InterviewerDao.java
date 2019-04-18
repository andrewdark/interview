package ua.pp.darknsoft.dao.interfaces;

import ua.pp.darknsoft.domain.entity.Interviewer;

/**
 * <p>
 *     This class is DAO interface for entity {@link ua.pp.darknsoft.domain.entity.Interviewer}.
 * </p>
 *
 * @author <a href='mailto:antoxalanio@gmail.com'>Anton Lomakin</a>
 *
 * @since 1.0
 */

public interface InterviewerDao extends GenericDao<Interviewer, Long> {

    Interviewer findByEmail(String email);
}
