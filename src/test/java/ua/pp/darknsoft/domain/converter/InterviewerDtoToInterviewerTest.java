package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.entity.Interviewer;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InterviewerDtoToInterviewerTest {
    private static final Long ID = 99L;
    private static final String FIRST_NAME = "Jack";
    private static final String LAST_NAME = "Wolfskin";
    private static final String EMAIL = "jack_wolfskin@gmail.com";

    private InterviewerDtoToInterviewer converter;

    @Before
    public void setUp() throws Exception {
        converter = new InterviewerDtoToInterviewer();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new InterviewerDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        InterviewerDto interviewerDto = new InterviewerDto();
        interviewerDto.setId(ID);
        interviewerDto.setFirstName(FIRST_NAME);
        interviewerDto.setLastName(LAST_NAME);
        interviewerDto.setEmail(EMAIL);
        //when
        Interviewer interviewer = converter.convert(interviewerDto);
        //then
        assertNotNull(interviewer);
        assertEquals(ID, interviewer.getId());
        assertEquals(FIRST_NAME, interviewer.getFirstName());
        assertEquals(LAST_NAME, interviewer.getLastName());
        assertEquals(EMAIL, interviewer.getEmail());

    }
}
