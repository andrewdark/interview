package ua.pp.darknsoft.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.pp.darknsoft.domain.entity.Status;
import ua.pp.darknsoft.util.LocalDateDeserializer;
import ua.pp.darknsoft.util.LocalDateSerializer;

import java.time.LocalDate;

public class FilterInterviewBuilder {
    private final Long id;
    private final String position;
    private final Status status;
    @JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate date;
    private final String firstName;
    private final String lastName;
    private final String email;

    public FilterInterviewBuilder(Builder builder) {
        this.id = builder.id;
        this.position = builder.position;
        this.status = builder.status;
        this.date = builder.date;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

    public static class Builder {
        private Long id = 0L;
        private String position;
        private Status status;
        private LocalDate date;
        private String firstName;
        private String lastName;
        private String email;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withFirsName(String firsName) {
            this.firstName = firsName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public FilterInterviewBuilder build() {
            return new FilterInterviewBuilder(this);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
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
    }

    public Long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
