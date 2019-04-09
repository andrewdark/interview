package ua.pp.darknsoft.dao;

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
import ua.pp.darknsoft.domain.dto.FilterInterviewBuilder;
import ua.pp.darknsoft.domain.entity.Candidate;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.domain.entity.Interviewer;
import ua.pp.darknsoft.domain.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    private static final String EMAIL = "Winnfield@pf.com";
    private static final LocalDate I_DATE02 = LocalDate.of(2019, 1, 1);
    private static final LocalDate I_DATE03 = LocalDate.of(2019, 1, 28);
    private static final LocalDate I_DATE04 = LocalDate.of(2019, 1, 2);

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void initMet() {
        Interview interview01 = new Interview();
        interview01.setStatus(Status.INTERVIEWED);
        interview01.setDate(LocalDate.of(2019, 1, 1));
        interview01.setPosition("Manual QA");
        Interview interview02 = new Interview();
        interview02.setStatus(Status.INTERVIEWED);
        interview02.setDate(LocalDate.of(2019, 1, 2));
        interview02.setPosition("Java junior");
        Interview interview03 = new Interview();
        interview03.setStatus(Status.INTERVIEWED);
        interview03.setDate(LocalDate.of(2019, 1, 1));
        interview03.setPosition("HR manager");
        Interview interview04 = new Interview();
        interview04.setStatus(Status.SCHEDULED);
        interview04.setDate(LocalDate.of(2019, 12, 4));
        interview04.setPosition("FrontEnd Senior");
        Interview interview05 = new Interview();
        interview05.setStatus(Status.NOTQUALIFIED);
        interview05.setDate(LocalDate.of(2019, 1, 28));
        interview05.setPosition("Java junior");

        Candidate candidate01 = new Candidate();
        candidate01.setFirstName("Vincent");
        candidate01.setLastName("Vega");
        candidate01.setEmail("vv@pf.com");
        interview01.setCandidate(candidate01);

        Candidate candidate02 = new Candidate();
        candidate02.setFirstName("Jules");
        candidate02.setLastName("Winnfield");
        candidate02.setEmail("Winnfield@pf.com");
        interview02.setCandidate(candidate02);

        Candidate candidate03 = new Candidate();
        candidate03.setFirstName("Mia");
        candidate03.setLastName("Wallace");
        candidate03.setEmail("mia-w@pf.com");
        interview03.setCandidate(candidate03);

        Candidate candidate04 = new Candidate();
        candidate04.setFirstName("Winston");
        candidate04.setLastName("Wolfe");
        candidate04.setEmail("wwolfe@pf.com");
        interview04.setCandidate(candidate04);

        interview05.setCandidate(candidate01);

        interviewDao.save(interview01);
        interviewDao.save(interview02);
        interviewDao.save(interview03);
        interviewDao.save(interview04);
        interviewDao.save(interview05);
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
    public void getInterviewWithFilterParamPosition() {
        FilterInterviewBuilder fid = new FilterInterviewBuilder.Builder().withPosition(POSITION).build();
        List<Interview> interviewList = interviewDao.getFilteredInterviews(fid);
        assertEquals(2, interviewList.size());
        for (Interview inter_x : interviewList) {
            assertEquals(inter_x.getPosition(), POSITION);
        }
    }

    @Test
    public void getInterviewWithFilterParamDate() {

        FilterInterviewBuilder fid = new FilterInterviewBuilder.Builder().withDate(I_DATE02).build();
        List<Interview> interviewList = interviewDao.getFilteredInterviews(fid);
        assertEquals(2, interviewList.size());
        for (Interview inter_x : interviewList) {
            assertEquals(inter_x.getDate(), I_DATE02);
        }
    }

    @Test
    public void getInterviewWithFilterParamsDateAndPosition() {

        FilterInterviewBuilder fid = new FilterInterviewBuilder.Builder()
                .withPosition(POSITION).withDate(I_DATE03).build();
        List<Interview> interviewList = interviewDao.getFilteredInterviews(fid);
        assertEquals(1, interviewList.size());
    }

    @Test
    public void getInterviewWithFilterParamsDateAndEmail() {

        FilterInterviewBuilder fid = new FilterInterviewBuilder.Builder().withEmail(EMAIL).withDate(I_DATE04).build();
        List<Interview> interviewList = interviewDao.getFilteredInterviews(fid);
        assertEquals(1, interviewList.size());
        assertEquals(EMAIL, interviewList.get(0).getCandidate().getEmail());
    }
    @Test
    public void isInterviewExist(){
        // Given
        Interview interview01 = new Interview();
        interview01.setStatus(Status.INTERVIEWED);
        interview01.setDate(I_DATE02);
        interview01.setPosition("Manual QA");
        Candidate candidate01 = new Candidate();
        candidate01.setFirstName("Vincent");
        candidate01.setLastName("Vega");
        candidate01.setEmail("vv@pf.com");
        interview01.setCandidate(candidate01);

        Interview interview02 = new Interview();
        interview02.setStatus(Status.INTERVIEWED);
        interview02.setPosition("Cleaning manager");
        interview02.setDate(I_DATE04);
        interview02.setCandidate(candidate01);
        // When

        // Then
        assertTrue(interviewDao.isExist(interview01));
        assertFalse(interviewDao.isExist(interview02));
    }
}
