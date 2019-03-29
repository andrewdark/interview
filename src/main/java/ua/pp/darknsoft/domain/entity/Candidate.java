package ua.pp.darknsoft.domain.entity;

import javax.persistence.Entity;

@Entity
public class Candidate extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String email;
}
