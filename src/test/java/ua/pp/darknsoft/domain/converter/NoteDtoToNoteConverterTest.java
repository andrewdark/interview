package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Note;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NoteDtoToNoteConverterTest {
    private static final Long ID = 1L;
    private static final String CRITERIA = "This is a criteria";
    private static final String NOTE = "This is some note";
    private static final int SCORE = 4;
    private static final Long INTERVIEWER_ID = 3L;
    private static final String INTERVIEWER_FN = "Quentin";
    private static final String INTERVIEWER_LN = "Tarantino";
    private static final String INTERVIEWER_EMAIL = "qtarsntino@hotmail.com";

    private NoteDtoToNote converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteDtoToNote(new InterviewerDtoToInterviewer());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NoteDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NoteDto noteDto = new NoteDto();
        noteDto.setId(ID);
        noteDto.setCriteria(CRITERIA);
        noteDto.setNote(NOTE);
        noteDto.setScore(SCORE);
        InterviewerDto interviewerDto = new InterviewerDto();
        interviewerDto.setId(INTERVIEWER_ID);
        interviewerDto.setEmail(INTERVIEWER_EMAIL);
        interviewerDto.setFirstName(INTERVIEWER_FN);
        interviewerDto.setLastName(INTERVIEWER_LN);
        noteDto.setInterviewerDto(interviewerDto);
        //when
        Note note = converter.convert(noteDto);
        //then
        assertNotNull(note);
        assertEquals(ID, note.getId());
        assertEquals(CRITERIA, note.getCriteria());
        assertEquals(NOTE, note.getNote());
        assertEquals(SCORE, note.getScore());
        assertEquals(INTERVIEWER_ID, note.getInterviewer().getId());
        assertEquals(INTERVIEWER_EMAIL, note.getInterviewer().getEmail());
        assertEquals(INTERVIEWER_FN, note.getInterviewer().getFirstName());
        assertEquals(INTERVIEWER_LN, note.getInterviewer().getLastName());
    }
}
