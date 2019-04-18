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

/**
 * <p>
 * This class is DTO class was burned from entity.Interview.class
 * </p>
 *
 * @author <a href='mailto:samsonov.a@ukr.net'>Samsonov Andrew</a>
 * @since 1.0
 */

public class InterviewDto {

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
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterviewDto)) {
            return false;
        }

        InterviewDto that = (InterviewDto) o;

        if (position != null ? !position.equals(that.position) : that.position != null) {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null) {
            return false;
        }
        return candidateDto != null ? candidateDto.equals(that.candidateDto) : that.candidateDto == null;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (candidateDto != null ? candidateDto.hashCode() : 0);
        return result;
    }
}
