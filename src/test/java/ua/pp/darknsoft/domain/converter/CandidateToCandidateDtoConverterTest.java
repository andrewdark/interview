package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.CandidateDto;
import ua.pp.darknsoft.domain.entity.Candidate;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CandidateToCandidateDtoConverterTest {
    private static final Long ID = 8L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Malkovich";
    private static final String EMAIL = "JMalkovich@gmail.com";
    private static final String SKYPE = "hayabuza2";
    private static final String PHONE = "+380985694598";

    CandidateToCandidateDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new CandidateToCandidateDto();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Candidate()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Candidate candidate = new Candidate();
        candidate.setId(ID);
        candidate.setFirstName(FIRST_NAME);
        candidate.setLastName(LAST_NAME);
        candidate.setEmail(EMAIL);
        candidate.setSkype(SKYPE);
        candidate.setPhone(PHONE);
        //when
        CandidateDto candidateDto = converter.convert(candidate);
        //then
        assertNotNull(candidateDto);
        assertEquals(ID, candidateDto.getId());
        assertEquals(FIRST_NAME, candidateDto.getFirstName());
        assertEquals(LAST_NAME, candidateDto.getLastName());
        assertEquals(EMAIL, candidateDto.getEmail());
        assertEquals(SKYPE, candidateDto.getSkype());
        assertEquals(PHONE, candidateDto.getPhone());
    }
}
