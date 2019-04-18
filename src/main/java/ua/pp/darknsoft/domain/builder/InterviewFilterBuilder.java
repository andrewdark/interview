package ua.pp.darknsoft.domain.builder;

import ua.pp.darknsoft.domain.dto.InterviewFilterDto;
import ua.pp.darknsoft.domain.entity.Status;

import java.time.LocalDate;

/**
 * <p>
 * This class is class which build a filter for filtered searching interview in InterviewDao interface
 * </p>
 *
 * @author <a href='mailto:samsonov.a@ukr.net'>Samsonov Andrew</a>
 * @since 1.0
 */

public class InterviewFilterBuilder {
    private final InterviewFilterDto filterDto = new InterviewFilterDto();

    public InterviewFilterBuilder withId(Long id) {
        this.filterDto.setId(id);
        return this;
    }

    public InterviewFilterBuilder withPosition(String position) {
        this.filterDto.setPosition(position);
        return this;
    }

    public InterviewFilterBuilder withStatus(Status status) {
        this.filterDto.setStatus(status);
        return this;
    }

    public InterviewFilterBuilder withDate(LocalDate date) {
        this.filterDto.setDate(date);
        return this;
    }

    public InterviewFilterBuilder withFirsName(String firsName) {
        this.filterDto.setFirstName(firsName);
        return this;
    }

    public InterviewFilterBuilder withLastName(String lastName) {
        this.filterDto.setLastName(lastName);
        return this;
    }

    public InterviewFilterBuilder withEmail(String email) {
        this.filterDto.setEmail(email);
        return this;
    }

    public InterviewFilterDto build() {
        return filterDto;
    }

}
