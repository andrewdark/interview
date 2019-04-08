package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.entity.Interviewer;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InterviewerToInterviewDtoConverterTest {

    private static final Long ID = 9L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Malkovich";
    private static final String EMAIL = "JMalkovich@gmail.com";

    private InterviewerToInterviewerDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new InterviewerToInterviewerDto();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Interviewer()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Interviewer interviewer = new Interviewer();
        interviewer.setId(ID);
        interviewer.setFirstName(FIRST_NAME);
        interviewer.setLastName(LAST_NAME);
        interviewer.setEmail(EMAIL);
        //when
        InterviewerDto interviewerDto = converter.convert(interviewer);
        //then
        assertNotNull(interviewerDto);
        assertEquals(ID, interviewerDto.getId());
        assertEquals(FIRST_NAME, interviewerDto.getFirstName());
        assertEquals(LAST_NAME, interviewerDto.getLastName());
        assertEquals(EMAIL, interviewerDto.getEmail());

    }
}
