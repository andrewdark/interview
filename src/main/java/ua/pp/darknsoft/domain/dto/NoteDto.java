package ua.pp.darknsoft.domain.dto;

import ua.pp.darknsoft.domain.entity.Interview;
import ua.pp.darknsoft.domain.entity.Interviewer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NoteDto {
    private Long id;
    @NotNull
    private String criteria;
    @NotNull
    private String note;
    @NotNull
    @Pattern(regexp = "^[1-5]{1,1}$")
    private int score;
    @NotNull
    private InterviewDto interviewDto;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public InterviewDto getInterviewDto() {
        return interviewDto;
    }

    public void setInterviewDto(InterviewDto interviewDto) {
        this.interviewDto = interviewDto;
    }

    public InterviewerDto getInterviewerDto() {
        return interviewerDto;
    }

    public void setInterviewerDto(InterviewerDto interviewerDto) {
        this.interviewerDto = interviewerDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteDto)) return false;

        NoteDto noteDto = (NoteDto) o;

        if (!criteria.equals(noteDto.criteria)) return false;
        if (!interviewDto.equals(noteDto.interviewDto)) return false;
        return interviewerDto.equals(noteDto.interviewerDto);
    }

    @Override
    public int hashCode() {
        int result = criteria.hashCode();
        result = 31 * result + interviewDto.hashCode();
        result = 31 * result + interviewerDto.hashCode();
        return result;
    }
}
