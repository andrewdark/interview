package ua.pp.darknsoft.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.pp.darknsoft.domain.dto.NoteDto;
import ua.pp.darknsoft.domain.entity.Note;

@Component
public class NoteToNoteDto implements Converter<Note, NoteDto> {
    private final Object $lock = new Object[0];

    private InterviewerToInterviewerDto interviewerConverter;

    @Autowired
    public NoteToNoteDto(InterviewerToInterviewerDto interviewerConverter) {
        this.interviewerConverter = interviewerConverter;
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
            noteDto.setNote(note.getNote());
            noteDto.setScore(note.getScore());
            if (note.getInterviewer() != null) {
                noteDto.setInterviewerDto(interviewerConverter.convert(note.getInterviewer()));
            }

            return noteDto;
        }
    }
}
