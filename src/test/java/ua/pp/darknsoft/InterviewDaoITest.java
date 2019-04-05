package ua.pp.darknsoft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ua.pp.darknsoft.dao.configuration.PersistenceJPAConfig;
import ua.pp.darknsoft.dao.interfaces.InterviewDao;
import ua.pp.darknsoft.domain.entity.Candidate;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.domain.entity.Interviewer;
import ua.pp.darknsoft.domain.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Rollback
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class},
        loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class InterviewDaoITest {

    @Autowired
    private InterviewDao interviewDao;

    private static final String POSITION = "Java junior";

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void initMet() {
        Interview inter1 = new Interview();
        inter1.setStatus(Status.INTERVIEWED);
        inter1.setDate(LocalDate.of(2019, 1, 1));
        inter1.setPosition("Manual QA");
        Interview inter2 = new Interview();
        inter2.setStatus(Status.INTERVIEWED);
        inter2.setDate(LocalDate.of(2019, 1, 2));
        inter2.setPosition("Java junior");
        Interview inter3 = new Interview();
        inter3.setStatus(Status.INTERVIEWED);
        inter3.setDate(LocalDate.of(2019, 1, 1));
        inter3.setPosition("HR manager");
        Interview inter4 = new Interview();
        inter4.setStatus(Status.SCHEDULED);
        inter4.setDate(LocalDate.of(2019, 12, 4));
        inter4.setPosition("FrontEnd Senior");
        Interview inter5 = new Interview();
        inter5.setStatus(Status.NOTQUALIFIED);
        inter5.setDate(LocalDate.of(2019, 1, 28));
        inter5.setPosition("Java junior");

        Candidate candy1 = new Candidate();
        candy1.setFirstName("Vincent");
        candy1.setLastName("Vega");
        candy1.setEmail("vv@pf.com");
        inter1.setCandidate(candy1);

        Candidate candy2 = new Candidate();
        candy2.setFirstName("Jules");
        candy2.setLastName("Winnfield");
        candy2.setEmail("Winnfield@pf.com");
        inter2.setCandidate(candy2);

        Candidate candy3 = new Candidate();
        candy3.setFirstName("Mia");
        candy3.setLastName("Wallace");
        candy3.setEmail("mia-w@pf.com");
        inter3.setCandidate(candy3);

        Candidate candy4 = new Candidate();
        candy4.setFirstName("Winston");
        candy4.setLastName("Wolfe");
        candy4.setEmail("wwolfe@pf.com");
        inter4.setCandidate(candy4);

        inter5.setCandidate(candy1);

        interviewDao.save(inter1);
        interviewDao.save(inter2);
        interviewDao.save(inter3);
        interviewDao.save(inter4);
        interviewDao.save(inter5);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void findById() {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Colin");
        candidate.setLastName("Farrell");
        candidate.setEmail("one1@four.com");

        Interviewer interviewer = new Interviewer();
        interviewer.setFirstName("John");
        interviewer.setLastName("Doe");
        interviewer.setEmail("one2@two.com");

        Interview interview = new Interview();
        interview.setCandidate(candidate);
        interview.setDate(LocalDate.now());
        interview.setPosition("Junior Java");
        interview.setStatus(Status.INTERVIEWED);

        interview.getInterviewerSet().add(interviewer);

        interviewDao.save(interview);

        entityManager.flush();
        entityManager.clear();

        Long id = interview.getId();

        Interview saved = interviewDao.findById(id);
        assertNotNull(saved);
        assertNotNull(saved.getInterviewerSet());
        assertEquals(1, saved.getInterviewerSet().size());
        assertEquals(interview.getCandidate().getFirstName(), saved.getCandidate().getFirstName());
        assertEquals(interview.getStatus(), saved.getStatus());

        for (Interviewer interviewer1 : saved.getInterviewerSet()) {
            assertEquals(interviewer1.getEmail(), interviewer.getEmail());
        }
    }


    @Test
    public void getFilteredInterview_01() {
        Assertions.assertThrows(org.springframework.dao.InvalidDataAccessApiUsageException.class, () -> {
            interviewDao.getFilteredInterviews(null);
        });
    }

    @Test
    public void getFilteredInterview_02() {

        Interview inter = new Interview();
        Candidate candy = new Candidate();
        inter.setCandidate(candy);
        inter.setPosition("Java junior");
        List<Interview> interviewList = interviewDao.getFilteredInterviews(inter);
        assertEquals(2, interviewList.size());
        for (Interview inter_x : interviewList) {
            assertEquals(inter_x.getPosition(), POSITION);
        }
    }

    @Test
    public void getFilteredInterview_03() {

        Interview inter = new Interview();
        Candidate candy = new Candidate();
        inter.setCandidate(candy);
        inter.setDate(LocalDate.of(2019, 1, 1));
        List<Interview> interviewList = interviewDao.getFilteredInterviews(inter);
        assertEquals(2, interviewList.size());
        for (Interview inter_x : interviewList) {
            assertEquals(inter_x.getDate(), LocalDate.of(2019, 1, 1));
        }
    }

    @Test
    public void getFilteredInterview_04() {

        Interview inter = new Interview();
        Candidate candy = new Candidate();
        inter.setCandidate(candy);
        inter.setDate(LocalDate.of(2019, 1, 28));
        inter.setPosition(POSITION);
        List<Interview> interviewList = interviewDao.getFilteredInterviews(inter);
        assertEquals(1, interviewList.size());
    }

    @Test
    public void getFilteredInterview_05() {

        Interview inter = new Interview();
        Candidate candy = new Candidate();
        candy.setEmail("Winnfield@pf.com");
        inter.setCandidate(candy);
        inter.setDate(LocalDate.of(2019, 1, 2));
        List<Interview> interviewList = interviewDao.getFilteredInterviews(inter);
        assertEquals(1, interviewList.size());
        assertEquals("Winnfield@pf.com", interviewList.get(0).getCandidate().getEmail());
    }
}
