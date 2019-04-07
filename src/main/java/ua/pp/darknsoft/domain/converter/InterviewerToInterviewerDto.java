package ua.pp.darknsoft.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.entity.Interviewer;

@Component
public class InterviewerToInterviewerDto implements Converter<Interviewer, InterviewerDto> {
    private final Object $lock = new Object[0];

    @Nullable
    @Override
    public InterviewerDto convert(Interviewer interviewer) {
        synchronized ($lock) {
            if (interviewer == null) {
                return null;
            }
            final InterviewerDto interviewerDto = new InterviewerDto();
            interviewerDto.setId(interviewer.getId());
            interviewerDto.setEmail(interviewer.getEmail());
            interviewerDto.setFirstName(interviewer.getFirstName());
            interviewerDto.setLastName(interviewer.getLastName());
            return interviewerDto;
        }
    }
}
