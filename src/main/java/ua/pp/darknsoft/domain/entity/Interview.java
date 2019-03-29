package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Interview extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToMany(mappedBy = "interviewSet", cascade = CascadeType.PERSIST)
    private Set<Interviewer> interviewerSet = new HashSet<>();

    @OneToMany(mappedBy = "interview", cascade = CascadeType.PERSIST)
    private Set<Notes> notesSet = new HashSet<>();

    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(180)")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @Column(name = "last_name", nullable = true, columnDefinition = "VARCHAR(180)")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]{2,255}$")
    @Column(nullable = false)
    private String position;

    @Version
    private Long version;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Set<Interviewer> getInterviewerSet() {
        return interviewerSet;
    }

    public void setInterviewerSet(Set<Interviewer> interviewerSet) {
        this.interviewerSet = interviewerSet;
    }

    public Set<Notes> getNotesSet() {
        return notesSet;
    }

    public void setNotesSet(Set<Notes> notesSet) {
        this.notesSet = notesSet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
