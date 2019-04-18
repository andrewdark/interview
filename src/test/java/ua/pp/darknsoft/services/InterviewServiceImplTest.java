package ua.pp.darknsoft.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.pp.darknsoft.domain.dto.CandidateDto;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Status;
import ua.pp.darknsoft.service.implementations.InterviewServiceImpl;
import ua.pp.darknsoft.service.interfaces.InterviewService;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <p>
 * This class is unit test of {@link InterviewServiceImpl}
 * </p>
 *
 * @author <a href='mailto:samsonov.a@ukr.net'>Andrew Samsonov</a>
 * @since 1.0
 */

public class InterviewServiceImplTest {

    private InterviewDto interviewDto;
    private InterviewService interviewService;

    public InterviewServiceImplTest() {
        this.interviewService = new InterviewServiceImpl();
    }

    @BeforeEach
    public void initInterviewDto() {
        this.interviewDto = new InterviewDto();

        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName("Vincent");
        candidateDto.setLastName("Vega");
        candidateDto.setEmail("vv@pf.com");
        interviewDto.setDate(LocalDate.of(2019, 12, 4));
        interviewDto.setPosition("Junior");
        interviewDto.setStatus(Status.HOLD);
        interviewDto.setCandidateDto(candidateDto);
    }

    @Test
    public void checkInterviewDtoAsNullBeforeCreate() {

        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(null);
                });
    }

    @Test
    public void checkInterviewDtoAsNullBeforeUpdate() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(null);
                });
    }

    @Test
    public void checkInterviewCandidateDtoFieldAsNullBeforeUpdating() {

        //when
        interviewDto.setCandidateDto(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void checkInterviewCandidateDtoEmailFieldAsNullBeforeUpdating() {

        //when
        interviewDto.getCandidateDto().setEmail(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void checkInterviewCandidateDtoFirstNameFieldAsNullBeforeUpdating() {

        //when
        interviewDto.getCandidateDto().setFirstName(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void checkInterviewCandidateDtoLastNameFieldAsNullBeforeUpdating() {

        //when
        interviewDto.getCandidateDto().setLastName(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void checkInterviewCandidateDtoDateFieldAsNullBeforeUpdating() {

        //when
        interviewDto.setDate(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void checkInterviewCandidateDtoPositionFieldAsNullBeforeUpdating() {

        //when
        interviewDto.setPosition(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void checkInterviewDtoStatusFieldAsNullBeforeUpdating() {

        //when
        interviewDto.setStatus(null);
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    interviewService.update(interviewDto);
                });
    }

    @Test
    public void makeSomeNoteByInterviewer() {
        //given

        InterviewerDto interviewerDto01 = new InterviewerDto();
        interviewerDto01.setFirstName("Vincent");
        interviewerDto01.setLastName("Vega");
        interviewerDto01.setEmail("vv@pf.com");
        interviewDto.getInterviewerDtoSet().add(interviewerDto01);

        InterviewerDto interviewerDto02 = new InterviewerDto();
        interviewerDto02.setFirstName("Jules");
        interviewerDto02.setLastName("Winnfield");
        interviewerDto02.setEmail("Winnfield@pf.com");
        interviewDto.getInterviewerDtoSet().add(interviewerDto02);

        InterviewerDto interviewerDto03 = new InterviewerDto();
        interviewerDto03.setFirstName("Mia");
        interviewerDto03.setLastName("Wallace");
        interviewerDto03.setEmail("mia-w@pf.com");
        interviewDto.getInterviewerDtoSet().add(interviewerDto03);

        NoteDto noteDto01 = new NoteDto();
        noteDto01.setCriteria("English");
        noteDto01.setNoteText("intermediate");
        noteDto01.setScore(3);
        noteDto01.setInterviewerDto(interviewerDto01);
        interviewDto.getNoteDtoSet().add(noteDto01);

        NoteDto noteDto02 = new NoteDto();
        noteDto02.setCriteria("OOP paradigm");
        noteDto02.setNoteText("excellent knowledge");
        noteDto02.setScore(5);
        noteDto02.setInterviewerDto(interviewerDto02);
        interviewDto.getNoteDtoSet().add(noteDto02);

        //when
        Map<InterviewerDto, Boolean> map = interviewService.hasNote(interviewDto);
        map.entrySet().stream().filter(x -> x.getValue()).count();

        //then
        assertEquals(3, map.size());
        assertEquals(2, map.entrySet().stream().filter(x -> x.getValue()).count());
        assertEquals(1, map.entrySet().stream().filter(x -> !x.getValue()).count());
    }

}
