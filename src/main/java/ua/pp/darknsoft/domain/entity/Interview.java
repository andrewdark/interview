package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Interview extends AbstractEntity{

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Candidate candy;
    @ManyToMany(mappedBy = "interview", cascade = CascadeType.PERSIST)
    private Set<Interviewers> interSet = new HashSet<>();
    @OneToMany(mappedBy = "interview", cascade = CascadeType.PERSIST)
    private Set<Notes> notesSet = new HashSet<>();
    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private String position;
    @Version
    private Long version;

    public Candidate getCandy() {
        return candy;
    }

    public void setCandy(Candidate candy) {
        this.candy = candy;
    }

    public Set<Interviewers> getInterSet() {
        return interSet;
    }

    public void setInterSet(Set<Interviewers> interSet) {
        this.interSet = interSet;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
