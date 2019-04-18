package ua.pp.darknsoft.dao.implementations;

import ua.pp.darknsoft.dao.interfaces.NoteDao;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.domain.entity.Interviewer;
import ua.pp.darknsoft.domain.entity.Note;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *     This class is implementations class of {@link ua.pp.darknsoft.dao.interfaces.NoteDao}.
 * </p>
 *
 * @author <a href='mailto:ponomarenko.bogdan.5@gmail.com'>Bogdan Ponomarenko</a>
 *
 * @since 1.0
 */
@Repository
public class NoteDaoImpl extends GenericDaoImpl<Note, Long> implements NoteDao {

    public NoteDaoImpl() {
        super(Note.class);
    }

    @Override
    public List<Note> findByInterviewAndInterviewer(Interview interview, Interviewer interviewer) {
        Assert.notNull(interview, "interview cannot be NULL");
        Assert.notNull(interviewer, "interviewer cannot be NULL");
        return getEntityManager().createQuery("FROM " + getClazz().getSimpleName() +
                " n WHERE n.interview =:interview AND n.interviewer =:interviewer", getClazz())
                .setParameter("interview", interview)
                .setParameter("interviewer", interviewer)
                .getResultList();
    }
}