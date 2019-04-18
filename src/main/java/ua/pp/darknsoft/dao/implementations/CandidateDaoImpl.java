package ua.pp.darknsoft.dao.implementations;

import ua.pp.darknsoft.dao.interfaces.CandidateDao;
import ua.pp.darknsoft.domain.entity.Candidate;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     This class is implementations class for {@link ua.pp.darknsoft.dao.interfaces.CandidateDao}.
 * </p>
 *
 * @author <a href='mailto:antoxalanio@gmail.com'>Anton Lomakin</a>
 *
 * @since 1.0
 */

@Repository
public class CandidateDaoImpl extends GenericDaoImpl<Candidate, Long> implements CandidateDao {

    public CandidateDaoImpl() { super(Candidate.class); }
}
