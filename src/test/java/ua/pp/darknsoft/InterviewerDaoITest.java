package ua.pp.darknsoft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.pp.darknsoft.dao.configuration.PersistenceJPAConfig;
import ua.pp.darknsoft.dao.interfaces.InterviewerDao;
import ua.pp.darknsoft.domain.entity.Interviewer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Rollback
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class},
        loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class InterviewerDaoITest {

    private final InterviewerDao interviewerDao;

    private EntityManager manager;

    @Autowired
    public InterviewerDaoITest(InterviewerDao interviewerDao) {
        this.interviewerDao = interviewerDao;
    }

    @PersistenceContext
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    @Test
    public void findById() {

        Interviewer interviewer = new Interviewer();
        interviewer.setFirstName("John");
        interviewer.setLastName("Doe");
        interviewer.setEmail("one@two.com");

        interviewerDao.save(interviewer);

        manager.flush();
        manager.clear();

        Long id = interviewer.getId();
        assertNotNull(id);
        assertNotNull(interviewer.getFirstName());

        Interviewer saved = interviewerDao.findById(id);
        assertNotNull(saved);

        assertEquals(saved.getEmail(), interviewer.getEmail());
    }
}
