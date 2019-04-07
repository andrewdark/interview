package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Interviewer;
import ua.pp.darknsoft.domain.entity.Note;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class NoteToNoteDtoConverterTest {
    private static final Long ID = 1L;
    private static final String CRITERIA = "This is a criteria";
    private static final String NOTE = "This is some note";
    private static final int SCORE = 4;
    private static final Long INTERVIEWER_ID = 3L;
    private static final String INTERVIEWER_FN = "Quentin";
    private static final String INTERVIEWER_LN = "Tarantino";
    private static final String INTERVIEWER_EMAIL = "qtarsntino@hotmail.com";

    private NoteToNoteDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteToNoteDto(new InterviewerToInterviewerDto());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Note()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Note note = new Note();
        note.setId(ID);
        note.setCriteria(CRITERIA);
        note.setNote(NOTE);
        note.setScore(SCORE);
        Interviewer interviewer = new Interviewer();
        interviewer.setId(INTERVIEWER_ID);
        interviewer.setEmail(INTERVIEWER_EMAIL);
        interviewer.setFirstName(INTERVIEWER_FN);
        interviewer.setLastName(INTERVIEWER_LN);
        note.setInterviewer(interviewer);
        //when
        NoteDto noteDto = converter.convert(note);
        //then
        assertNotNull(noteDto);
        assertEquals(ID, noteDto.getId());
        assertEquals(CRITERIA, noteDto.getCriteria());
        assertEquals(NOTE, noteDto.getNote());
        assertEquals(SCORE, noteDto.getScore());
        assertEquals(INTERVIEWER_ID, noteDto.getInterviewerDto().getId());
        assertEquals(INTERVIEWER_EMAIL, noteDto.getInterviewerDto().getEmail());
        assertEquals(INTERVIEWER_FN, noteDto.getInterviewerDto().getFirstName());
        assertEquals(INTERVIEWER_LN, noteDto.getInterviewerDto().getLastName());
    }
}
