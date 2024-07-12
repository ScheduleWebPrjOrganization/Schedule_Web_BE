package ac.su.schedule_web_prj_be.domain;

public enum TaskStatus {
    DONE("Done"),
    IN_PROGRESS("In Progress"),
    NOT_DONE("Not Done");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
