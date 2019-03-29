package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Notes extends AbstractEntity {

    @Column (nullable = false)
    private String criteria;

    @Column (nullable = false)
    private String note;

    @Pattern(regexp ="^[1-5]{1,1}$")
    @Column(nullable = false)
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", nullable = false)
    private Interview interview;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }
}
