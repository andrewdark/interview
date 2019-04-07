package ua.pp.darknsoft.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.pp.darknsoft.domain.entity.Status;
import ua.pp.darknsoft.util.LocalDateDeserializer;
import ua.pp.darknsoft.util.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class InterviewDto {
    @NotNull
    private Long id;
    @NotNull
    private String position;
    @NotNull
    private Status status;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    @NotNull
    private CandidateDto candidateDto;

    private Set<NoteDto> noteDtoSet = new HashSet<>();
    @NotNull
    private Set<InterviewerDto> interviewerDtoSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CandidateDto getCandidateDto() {
        return candidateDto;
    }

    public void setCandidateDto(CandidateDto candidateDto) {
        this.candidateDto = candidateDto;
    }

    public Set<NoteDto> getNoteDtoSet() {
        return noteDtoSet;
    }

    public void setNoteDtoSet(Set<NoteDto> noteDtoSet) {
        this.noteDtoSet = noteDtoSet;
    }

    public Set<InterviewerDto> getInterviewerDtoSet() {
        return interviewerDtoSet;
    }

    public void setInterviewerDtoSet(Set<InterviewerDto> interviewerDtoSet) {
        this.interviewerDtoSet = interviewerDtoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterviewDto)) return false;

        InterviewDto that = (InterviewDto) o;

        if (!position.equals(that.position)) return false;
        if (!date.equals(that.date)) return false;
        return candidateDto.equals(that.candidateDto);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + candidateDto.hashCode();
        return result;
    }
}
