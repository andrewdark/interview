package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.CandidateDto;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.domain.entity.Status;

import java.time.LocalDate;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InterviewDtoToInterviewConverterTest {
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

    InterviewDtoToInterview converter;

    @Before
    public void setUp() throws Exception {
        converter = new InterviewDtoToInterview(new CandidateDtoToCandidate(),
                new InterviewerDtoToInterviewer(),
                new NoteDtoToNote(new InterviewerDtoToInterviewer()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new InterviewDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        InterviewDto interviewDto = new InterviewDto();
        interviewDto.setId(ID);
        interviewDto.setPosition(POSITION);
        interviewDto.setStatus(STATUS);
        interviewDto.setDate(DATE);

        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(CANDIDATE_ID);
        candidateDto.setFirstName(CANDIDATE_FIRST_NAME);
        candidateDto.setLastName(CANDIDATE_LAST_NAME);
        candidateDto.setEmail(CANDIDATE_EMAIL);
        candidateDto.setSkype(CANDIDATE_SKYPE);
        candidateDto.setPhone(CANDIDATE_PHONE);

        InterviewerDto interviewerDto01 = new InterviewerDto();
        interviewerDto01.setId(INTERVIEWER_ID_01);
        interviewerDto01.setFirstName(INTERVIEWER_FN_01);
        interviewerDto01.setLastName(INTERVIEWER_LN_01);
        interviewerDto01.setEmail(INTERVIEWER_EMAIL_01);
        InterviewerDto interviewerDto02 = new InterviewerDto();
        interviewerDto02.setId(INTERVIEWER_ID_02);
        interviewerDto02.setFirstName(INTERVIEWER_FN_02);
        interviewerDto02.setLastName(INTERVIEWER_LN_02);
        interviewerDto02.setEmail(INTERVIEWER_EMAIL_02);
        InterviewerDto interviewerDto03 = new InterviewerDto();
        interviewerDto03.setId(INTERVIEWER_ID_03);
        interviewerDto03.setFirstName(INTERVIEWER_FN_03);
        interviewerDto03.setLastName(INTERVIEWER_LN_03);
        interviewerDto03.setEmail(INTERVIEWER_EMAIL_03);

        NoteDto noteDto01 = new NoteDto();
        noteDto01.setId(NOTE_ID_01);
        noteDto01.setCriteria(NOTE_CRITERIA_01);
        noteDto01.setNoteText(NOTE_NOTE_01);
        noteDto01.setScore(NOTE_SCORE_01);
        noteDto01.setInterviewerDto(interviewerDto01);
        NoteDto noteDto02 = new NoteDto();
        noteDto02.setId(NOTE_ID_02);
        noteDto02.setCriteria(NOTE_CRITERIA_02);
        noteDto02.setNoteText(NOTE_NOTE_02);
        noteDto02.setScore(NOTE_SCORE_02);
        noteDto02.setInterviewerDto(interviewerDto02);
        NoteDto noteDto03 = new NoteDto();
        noteDto03.setId(NOTE_ID_03);
        noteDto03.setCriteria(NOTE_CRITERIA_03);
        noteDto03.setNoteText(NOTE_NOTE_03);
        noteDto03.setScore(NOTE_SCORE_03);
        noteDto03.setInterviewerDto(interviewerDto03);

        interviewDto.setCandidateDto(candidateDto);
        interviewDto.getInterviewerDtoSet().add(interviewerDto01);
        interviewDto.getInterviewerDtoSet().add(interviewerDto02);
        interviewDto.getInterviewerDtoSet().add(interviewerDto03);

        interviewDto.getNoteDtoSet().add(noteDto01);
        interviewDto.getNoteDtoSet().add(noteDto02);
        interviewDto.getNoteDtoSet().add(noteDto03);

        //when
        Interview interview = converter.convert(interviewDto);

        //then
        assertNotNull(interview);
        assertEquals(ID, interview.getId());
        assertEquals(POSITION, interview.getPosition());
        assertEquals(STATUS, interview.getStatus());
        assertEquals(DATE, interview.getDate());
        assertEquals(CANDIDATE_ID, interview.getCandidate().getId());
        assertEquals(3, interview.getInterviewerSet().size());
        assertEquals(3, interview.getNoteSet().size());
    }

}
