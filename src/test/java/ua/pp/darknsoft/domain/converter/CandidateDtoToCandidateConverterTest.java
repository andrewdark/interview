package ua.pp.darknsoft.domain.converter;

import org.junit.Before;
import org.junit.Test;
import ua.pp.darknsoft.domain.dto.CandidateDto;
import ua.pp.darknsoft.domain.entity.Candidate;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CandidateDtoToCandidateConverterTest {
    private static final Long ID = 38L;
    private static final String FIRST_NAME = "Tom";
    private static final String LAST_NAME = "Walker";
    private static final String EMAIL = "tom_walker@gmail.com";
    private static final String SKYPE = "tw_wt_skype";
    private static final String PHONE = "+140935624598";

    CandidateDtoToCandidate converter;

    @Before
    public void setUp() throws Exception {
        converter = new CandidateDtoToCandidate();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CandidateDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(ID);
        candidateDto.setFirstName(FIRST_NAME);
        candidateDto.setLastName(LAST_NAME);
        candidateDto.setEmail(EMAIL);
        candidateDto.setSkype(SKYPE);
        candidateDto.setPhone(PHONE);
        //when
        Candidate candidate = converter.convert(candidateDto);
        //then
        assertNotNull(candidate);
        assertEquals(ID, candidate.getId());
        assertEquals(FIRST_NAME, candidate.getFirstName());
        assertEquals(LAST_NAME, candidate.getLastName());
        assertEquals(EMAIL, candidate.getEmail());
        assertEquals(SKYPE, candidate.getSkype());
        assertEquals(PHONE, candidate.getPhone());
    }
}
