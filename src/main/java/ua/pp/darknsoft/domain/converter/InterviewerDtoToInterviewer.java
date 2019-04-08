package ua.pp.darknsoft.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import ua.pp.darknsoft.domain.dto.InterviewerDto;
import ua.pp.darknsoft.domain.entity.Interviewer;

public class InterviewerDtoToInterviewer implements Converter<InterviewerDto, Interviewer> {

    private final Object $lock = new Object[0];

    @Nullable
    @Override
    public Interviewer convert(InterviewerDto interviewerDto) {
        synchronized ($lock) {
            if (interviewerDto == null) {
                return null;
            }
            final Interviewer interviewer = new Interviewer();
            interviewer.setId(interviewerDto.getId());
            interviewer.setEmail(interviewerDto.getEmail());
            interviewer.setFirstName(interviewerDto.getFirstName());
            interviewer.setLastName(interviewerDto.getLastName());

            return interviewer;
        }
    }
}
