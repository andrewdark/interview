package ua.pp.darknsoft.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Note;

@Component
public class NotesToNotesDto implements Converter<Note, NoteDto> {
    private final Object $lock = new Object[0];

    private InterviewerToInterviewerDto interviewerConverter;
    private InterviewToInterviewDto interviewConverter;

    @Autowired
    public NotesToNotesDto(InterviewerToInterviewerDto interviewerConverter, InterviewToInterviewDto interviewConverter) {
        this.interviewerConverter = interviewerConverter;
        this.interviewConverter = interviewConverter;
    }

    @Nullable
    @Override
    public NoteDto convert(Note note) {
        synchronized ($lock) {
            if (note == null) {
                return null;
            }
            final NoteDto noteDto = new NoteDto();
            noteDto.setId(note.getId());
            noteDto.setCriteria(note.getCriteria());
            noteDto.setScore(note.getScore());
            if (note.getInterview() != null) {
                noteDto.setInterviewDto(interviewConverter.convert(note.getInterview()));
            }
            if (note.getInterviewer() != null) {
                noteDto.setInterviewerDto(interviewerConverter.convert(note.getInterviewer()));
            }
            
            return noteDto;
        }
    }
}
