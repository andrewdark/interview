package ua.pp.darknsoft.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.entity.Interview;

@Component
public class InterviewToInterviewDto implements Converter<Interview, InterviewDto> {

    private final Object $lock = new Object[0];

    private CandidateToCandidateDto candidateConverter;
    private InterviewerToInterviewerDto interviewerConverter;
    private NotesToNotesDto notesConverter;

    @Autowired
    public InterviewToInterviewDto(CandidateToCandidateDto candidateConverter, InterviewerToInterviewerDto interviewerConverter, NotesToNotesDto notesConverter) {
        this.candidateConverter = candidateConverter;
        this.interviewerConverter = interviewerConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Override
    public InterviewDto convert(Interview interview) {
        synchronized ($lock) {
            if (interview == null) {
                return null;
            }
            final InterviewDto interviewDto = new InterviewDto();
            interviewDto.setId(interview.getId());
            interviewDto.setDate(interview.getDate());
            interviewDto.setPosition(interview.getPosition());
            interviewDto.setStatus(interview.getStatus());

            if (interview.getCandidate() != null) {
                interviewDto.setCandidateDto(candidateConverter.convert(interview.getCandidate()));
            }

            if (interview.getInterviewerSet() != null && interview.getInterviewerSet().size() > 0) {
                interview.getInterviewerSet()
                        .forEach(inter -> interviewDto.getInterviewerDtoSet()
                                .add(interviewerConverter.convert(inter)));
            }

            if (interview.getNoteSet() != null && interview.getNoteSet().size() > 0) {
                interview.getNoteSet().forEach(notes -> interviewDto.getNoteDtoSet().add(notesConverter.convert(notes)));
            }

            return interviewDto;
        }
    }
}
