package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Interview extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "candidate_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_INTERVIEW_CANDIDATE"))
    private Candidate candidate;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "INTERVIEW_INTERVIEWER",
            joinColumns = @JoinColumn(name = "interview_id", foreignKey = @ForeignKey(name = "FK_INTER_INTERVIEW")),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id", foreignKey = @ForeignKey(name = "FK_INTER_INTERVIEWER")))
    private Set<Interviewer> interviewerSet = new HashSet<>();

    @OneToMany(mappedBy = "interview", cascade = CascadeType.PERSIST)
    private Set<Note> noteSet = new HashSet<>();

    @Column(nullable = false)

    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

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

    public Set<Note> getNoteSet() {
        return noteSet;
    }

    public void setNoteSet(Set<Note> noteSet) {
        this.noteSet = noteSet;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;
        Interview interview = (Interview) o;
        return candidate.equals(interview.candidate) &&
                date.equals(interview.date) &&
                position.equals(interview.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidate, date, position);
    }
}
