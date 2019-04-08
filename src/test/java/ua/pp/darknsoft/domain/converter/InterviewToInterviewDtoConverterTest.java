package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.entity.*;

import java.time.LocalDate;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InterviewToInterviewDtoConverterTest {

    private static final Long ID = 129L;
    private static final String POSITION = "Java junior";
    private static final Status STATUS = Status.INTERVIEWED;
    private static final LocalDate DATE = LocalDate.of(2019, 4, 9);


    private static final Long CANDIDATE_ID = 1L;
    private static final String CANDIDATE_FIRST_NAME = "John";
    private static final String CANDIDATE_LAST_NAME = "Malkovich";
    private static final String CANDIDATE_EMAIL = "JMalkovich@gmail.com";
    private static final String CANDIDATE_SKYPE = "hayabuza2";
    private static final String CANDIDATE_PHONE = "+380985694598";

    private static final Long INTERVIEWER_ID_01 = 1L;
    private static final String INTERVIEWER_FN_01 = "Quentin";
    private static final String INTERVIEWER_LN_01 = "Tarantino";
    private static final String INTERVIEWER_EMAIL_01 = "qtarsntino@hotmail.com";

    private static final Long INTERVIEWER_ID_02 = 2L;
    private static final String INTERVIEWER_FN_02 = "Wes";
    private static final String INTERVIEWER_LN_02 = "Anderson";
    private static final String INTERVIEWER_EMAIL_02 = "w-anderson@hotmail.com";

    private static final Long INTERVIEWER_ID_03 = 3L;
    private static final String INTERVIEWER_FN_03 = "Christopher";
    private static final String INTERVIEWER_LN_03 = "Nolan";
    private static final String INTERVIEWER_EMAIL_03 = "ch_nolan@hotmail.com";

    private static final Long NOTE_ID_01 = 1L;
    private static final String NOTE_CRITERIA_01 = "This is a criteria 01";
    private static final String NOTE_NOTE_01 = "This is some note 01";
    private static final int NOTE_SCORE_01 = 1;

    private static final Long NOTE_ID_02 = 2L;
    private static final String NOTE_CRITERIA_02 = "This is a criteria 02";
    private static final String NOTE_NOTE_02 = "This is some note 02";
    private static final int NOTE_SCORE_02 = 2;

    private static final Long NOTE_ID_03 = 3L;
    private static final String NOTE_CRITERIA_03 = "This is a criteria 03";
    private static final String NOTE_NOTE_03 = "This is some note 03";
    private static final int NOTE_SCORE_03 = 3;

    InterviewToInterviewDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new InterviewToInterviewDto(new CandidateToCandidateDto(),
                new InterviewerToInterviewerDto(),
                new NoteToNoteDto(new InterviewerToInterviewerDto()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Interview()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Interview interview = new Interview();
        interview.setId(ID);
        interview.setPosition(POSITION);
        interview.setStatus(STATUS);
        interview.setDate(DATE);

        Candidate candidate = new Candidate();
        candidate.setId(CANDIDATE_ID);
        candidate.setFirstName(CANDIDATE_FIRST_NAME);
        candidate.setLastName(CANDIDATE_LAST_NAME);
        candidate.setEmail(CANDIDATE_EMAIL);
        candidate.setSkype(CANDIDATE_SKYPE);
        candidate.setPhone(CANDIDATE_PHONE);

        Interviewer interviewer01 = new Interviewer();
        interviewer01.setId(INTERVIEWER_ID_01);
        interviewer01.setFirstName(INTERVIEWER_FN_01);
        interviewer01.setLastName(INTERVIEWER_LN_01);
        interviewer01.setEmail(INTERVIEWER_EMAIL_01);
        Interviewer interviewer02 = new Interviewer();
        interviewer02.setId(INTERVIEWER_ID_02);
        interviewer02.setFirstName(INTERVIEWER_FN_02);
        interviewer02.setLastName(INTERVIEWER_LN_02);
        interviewer02.setEmail(INTERVIEWER_EMAIL_02);
        Interviewer interviewer03 = new Interviewer();
        interviewer03.setId(INTERVIEWER_ID_03);
        interviewer03.setFirstName(INTERVIEWER_FN_03);
        interviewer03.setLastName(INTERVIEWER_LN_03);
        interviewer03.setEmail(INTERVIEWER_EMAIL_03);

        Note note01 = new Note();
        note01.setId(NOTE_ID_01);
        note01.setCriteria(NOTE_CRITERIA_01);
        note01.setNote(NOTE_NOTE_01);
        note01.setScore(NOTE_SCORE_01);
        note01.setInterviewer(interviewer01);
        Note note02 = new Note();
        note02.setId(NOTE_ID_02);
        note02.setCriteria(NOTE_CRITERIA_02);
        note02.setNote(NOTE_NOTE_02);
        note02.setScore(NOTE_SCORE_02);
        note02.setInterviewer(interviewer02);
        Note note03 = new Note();
        note03.setId(NOTE_ID_03);
        note03.setCriteria(NOTE_CRITERIA_03);
        note03.setNote(NOTE_NOTE_03);
        note03.setScore(NOTE_SCORE_03);
        note03.setInterviewer(interviewer03);

        interview.setCandidate(candidate);
        interview.getInterviewerSet().add(interviewer01);
        interview.getInterviewerSet().add(interviewer02);
        interview.getInterviewerSet().add(interviewer03);

        interview.getNoteSet().add(note01);
        interview.getNoteSet().add(note02);
        interview.getNoteSet().add(note03);

        //when
        InterviewDto interviewDto = converter.convert(interview);

        //then
        assertNotNull(interviewDto);
        assertEquals(ID, interviewDto.getId());
        assertEquals(POSITION, interviewDto.getPosition());
        assertEquals(STATUS, interviewDto.getStatus());
        assertEquals(DATE, interviewDto.getDate());
        assertEquals(CANDIDATE_ID, interviewDto.getCandidateDto().getId());
        assertEquals(3, interviewDto.getInterviewerDtoSet().size());
        assertEquals(3, interviewDto.getNoteDtoSet().size());

    }


}
