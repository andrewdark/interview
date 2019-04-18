package ua.pp.darknsoft.dao.interfaces;

import ua.pp.darknsoft.domain.dto.InterviewFilterDto;
import ua.pp.darknsoft.domain.entity.Interview;

import java.util.List;

/**
 * <p>
 * This class is DAO interface for entity {@link ua.pp.darknsoft.domain.entity.Interview}.
 * </p>
 *
 * @author <a href='mailto:antoxalanio@gmail.com'>Anton Lomakin</a>
 * @author <a href='mailto:samsonov.a@ukr.net'>Samsonov Andrew</a>
 * @since 1.0
 */

public interface InterviewDao extends GenericDao<Interview, Long> {
    List<Interview> getInterviewWithFilter(InterviewFilterDto filter);
}
