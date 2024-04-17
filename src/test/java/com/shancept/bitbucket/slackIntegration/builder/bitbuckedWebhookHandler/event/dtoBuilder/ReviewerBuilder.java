package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Reviewer;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;

final public class ReviewerBuilder {
    public final static String ROLE = "reviewer_role";
    public final static boolean APPROVED = true;
    public final static String STATUS = "reviewer_status";

    private User user = new UserBuilder().build();
    private String role = ROLE;
    private boolean approved = APPROVED;
    private String status = STATUS;

    public ReviewerBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public ReviewerBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public ReviewerBuilder withApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public ReviewerBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public Reviewer build() {
        Reviewer reviewer = new Reviewer();
        reviewer.setUser(user);
        reviewer.setRole(role);
        reviewer.setApproved(approved);
        reviewer.setStatus(status);
        return reviewer;
    }
}
