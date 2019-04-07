package ua.pp.darknsoft.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.pp.darknsoft.domain.entity.Notes;
import ua.pp.darknsoft.domain.entity.Status;
import ua.pp.darknsoft.util.LocalDateDeserializer;
import ua.pp.darknsoft.util.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditInterviewDto {
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
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private List<Notes> notes = new ArrayList<>();

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditInterviewDto)) return false;
        EditInterviewDto that = (EditInterviewDto) o;
        return position.equals(that.position) &&
                date.equals(that.date) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, date, firstName, lastName, email);
    }
}
