package ua.pp.darknsoft.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UC_CANDIDATE_EMAIL", columnNames = "email")})
public class Candidate extends AbstractEntity {

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(180)")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z]{1,180}$")
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(180)")
    private String lastName;

    @Pattern(regexp = "/.+@.+\\..+/i")
    @Column(nullable = false)
    private String email;

    private String skype;

    private String phone;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST)
    private Set<Interview> interviewSet = new HashSet<>();

    @Version
    private Long version;

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

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Interview> getInterviewSet() {
        return interviewSet;
    }

    public void setInterviewSet(Set<Interview> interviewSet) {
        this.interviewSet = interviewSet;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
