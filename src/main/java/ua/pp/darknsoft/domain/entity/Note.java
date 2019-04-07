package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Note extends AbstractEntity {

    @Column(nullable = false)
    private String criteria;

    @Column(nullable = false)
    private String note;

    @Pattern(regexp = "^[1-5]{1,1}$")
    @Column(nullable = false)
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", nullable = false, foreignKey = @ForeignKey(name = "FK_NOTES_INTERVIEW"))
    private Interview interview;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false, foreignKey = @ForeignKey(name = "FK_NOTES_INTERVIEWER"))
    private Interviewer interviewer;

    @Version
    private Long version;

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
