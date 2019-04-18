package ua.pp.darknsoft.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    SCHEDULED("Scheduled Interview"),
    INTERVIEWED("Interviewed"),
    HOLD("Put On Hold"),
    OFFERED("Position Offered"),
    NOTQUALIFIED("Not Qualified");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
