package ua.pp.darknsoft.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.InterviewDto;
import ua.pp.darknsoft.domain.entity.Interview;

@Component
public class InterviewDtoToInterview implements Converter<InterviewDto, Interview> {

    private final Object $lock = new Object[0];

    private CandidateDtoToCandidate candidateDtoConverter;
    private InterviewerDtoToInterviewer interviewerDtoConverter;
    private NoteDtoToNote noteDtoConverter;

    @Autowired
    public InterviewDtoToInterview(CandidateDtoToCandidate candidateDtoConverter, InterviewerDtoToInterviewer interviewerDtoConverter, NoteDtoToNote noteDtoConverter) {
        this.candidateDtoConverter = candidateDtoConverter;
        this.interviewerDtoConverter = interviewerDtoConverter;
        this.noteDtoConverter = noteDtoConverter;
    }

    @Nullable
    @Override
    public Interview convert(InterviewDto interviewDto) {
        synchronized ($lock) {
            if (interviewDto == null) {
                return null;
            }
            final Interview interview = new Interview();
            interview.setId(interviewDto.getId());
            interview.setDate(interviewDto.getDate());
            interview.setPosition(interviewDto.getPosition());
            interview.setStatus(interviewDto.getStatus());

            if (interviewDto.getCandidateDto() != null) {
                interview.setCandidate(candidateDtoConverter.convert(interviewDto.getCandidateDto()));
            }

            if (interviewDto.getInterviewerDtoSet() != null && interviewDto.getInterviewerDtoSet().size() > 0) {
                interviewDto.getInterviewerDtoSet()
                        .forEach(interDto -> interview.getInterviewerSet()
                                .add(interviewerDtoConverter.convert(interDto)));
            }

            if (interviewDto.getNoteDtoSet() != null && interviewDto.getNoteDtoSet().size() > 0) {
                interviewDto.getNoteDtoSet().forEach(noteDto -> interview.getNoteSet().add(noteDtoConverter.convert(noteDto)));
            }

            return interview;
        }
    }
}
