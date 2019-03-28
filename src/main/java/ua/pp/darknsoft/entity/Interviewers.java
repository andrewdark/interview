package ua.pp.darknsoft.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Interviewers extends AbstractEntity {

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "INTERVIEW_INTERVIEWERS",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "interviewers_id"))
    private Set<Interview> interview = new HashSet<>();

    public Set<Interview> getInterview() {
        return interview;
    }

    public void setInterview(Set<Interview> interview) {
        this.interview = interview;
    }
}
