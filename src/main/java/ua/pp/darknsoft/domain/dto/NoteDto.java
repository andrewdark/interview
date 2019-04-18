package ua.pp.darknsoft.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * This class is DTO class was burned from entity.Note.class
 * </p>
 *
 * @author <a href='mailto:samsonov.a@ukr.net'>Samsonov Andrew</a>
 * @since 1.0
 */

public class NoteDto {

    private Long id;
    @NotNull
    private String criteria;
    @NotNull
    private String noteText;
    @NotNull
    @Pattern(regexp = "^[1-5]{1,1}$")
    private Integer score;
    @NotNull
    private InterviewerDto interviewerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public InterviewerDto getInterviewerDto() {
        return interviewerDto;
    }

    public void setInterviewerDto(InterviewerDto interviewerDto) {
        this.interviewerDto = interviewerDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteDto)) {
            return false;
        }

        NoteDto noteDto = (NoteDto) o;

        if (criteria != null ? !criteria.equals(noteDto.criteria) : noteDto.criteria != null) {
            return false;
        }
        if (noteText != null ? !noteText.equals(noteDto.noteText) : noteDto.noteText != null) {
            return false;
        }
        if (score != null ? !score.equals(noteDto.score) : noteDto.score != null) {
            return false;
        }
        return interviewerDto != null ? interviewerDto.equals(noteDto.interviewerDto) : noteDto.interviewerDto == null;
    }

    @Override
    public int hashCode() {
        int result = criteria != null ? criteria.hashCode() : 0;
        result = 31 * result + (noteText != null ? noteText.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (interviewerDto != null ? interviewerDto.hashCode() : 0);
        return result;
    }
}
