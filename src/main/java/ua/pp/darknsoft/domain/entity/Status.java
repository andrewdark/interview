package ua.pp.darknsoft.domain.entity;

public enum Status {
    SCHEDULED("Scheduled Interview"),
    INTERVIEWED("Interviewed"),
    HOLD("Put On Hold"),
    OFFERED("Position Offered"),
    NOTQUALIFIED("Not Qualified");

    private String value;

    Status(String value) {
        this.value = value;
    }

}
