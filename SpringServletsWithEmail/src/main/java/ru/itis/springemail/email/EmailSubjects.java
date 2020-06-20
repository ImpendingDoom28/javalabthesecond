package ru.itis.springemail.email;

public enum EmailSubjects {
    CONFIRMATION("WEB-INF/frontend/ftl/email/confirmation.ftl");

    private String pathToAttachment;

    EmailSubjects(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    public String attachment() {
        return pathToAttachment;
    }

    public String subject() {
        return this.name();
    }
}
