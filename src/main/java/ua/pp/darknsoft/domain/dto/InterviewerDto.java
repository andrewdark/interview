package ua.pp.darknsoft.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class InterviewerDto {
    private Long id;
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @NotNull
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @NotNull
    private String lastName;
    @Pattern(regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")
    @NotNull
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterviewerDto)) return false;

        InterviewerDto that = (InterviewerDto) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
