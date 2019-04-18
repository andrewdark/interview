package ua.pp.darknsoft.dao.interfaces;

import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.domain.entity.Interviewer;
import ua.pp.darknsoft.domain.entity.Note;

import java.util.List;

/**
 * <p>
 *     This class is DAO interface for entity {@link ua.pp.darknsoft.domain.entity.Note}.
 * </p>
 *
 * @author <a href='mailto:ponomarenko.bogdan.5@gmail.com'>Bogdan Ponomarenko</a>
 *
 * @since 1.0
 */

public interface NoteDao extends GenericDao<Note, Long> {
    List<Note> findByInterviewAndInterviewer(Interview interview, Interviewer interviewer);
}