package ua.pp.darknsoft.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Note;

@Component
public class NoteDtoToNote implements Converter<NoteDto, Note> {
    private final Object $lock = new Object[0];

    private InterviewerDtoToInterviewer interviewerDtoConverter;

    @Autowired
    public NoteDtoToNote(InterviewerDtoToInterviewer interviewerDtoConverter) {
        this.interviewerDtoConverter = interviewerDtoConverter;
    }

    @Nullable
    @Override
    public Note convert(NoteDto noteDto) {
        synchronized ($lock) {
            if (noteDto == null) {
                return null;
            }
            final Note note = new Note();
            note.setId(noteDto.getId());
            note.setCriteria(noteDto.getCriteria());
            note.setNote(noteDto.getNoteText());
            if (noteDto.getScore() != null) {
                note.setScore(noteDto.getScore());
            }
            if (noteDto.getInterviewerDto() != null) {
                note.setInterviewer(interviewerDtoConverter.convert(noteDto.getInterviewerDto()));
            }

            return note;
        }
    }
}
