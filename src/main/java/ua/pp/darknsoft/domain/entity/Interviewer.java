package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Interviewer extends AbstractEntity {

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(180)")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @Column(name = "last_name", nullable = true, columnDefinition = "VARCHAR(180)")
    private String lastName;

    @Pattern(regexp = "/.+@.+\\..+/i")
    @Column(nullable = true, unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "INTERVIEW_INTERVIEWER",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id"))
    private Set<Interview> interviewSet = new HashSet<>();

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

    public Set<Interview> getInterviewSet() {
        return interviewSet;
    }

    public void setInterviewSet(Set<Interview> interviewSet) {
        this.interviewSet = interviewSet;
    }
}
