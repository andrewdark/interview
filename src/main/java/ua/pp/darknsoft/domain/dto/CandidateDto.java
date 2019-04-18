package ua.pp.darknsoft.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * This class is DTO class was burned from entity.Candidate.class
 * </p>
 *
 * @author <a href='mailto:samsonov.a@ukr.net'>Samsonov Andrew</a>
 * @since 1.0
 */

public class CandidateDto {

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
    private String skype;
    private String phone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof CandidateDto)) {return false;}

        CandidateDto that = (CandidateDto) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {return false;}
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {return false;}
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
