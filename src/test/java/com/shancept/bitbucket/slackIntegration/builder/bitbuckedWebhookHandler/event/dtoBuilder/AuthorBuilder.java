package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Author;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;

final public class AuthorBuilder {
    public final static String ROLE = "author";
    public final static boolean APPROVED = false;
    public final static String STATUS = "author_status";

    private User user = new UserBuilder().build();
    private String role = ROLE;
    private boolean approved = APPROVED;
    private String status = STATUS;

    public AuthorBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public AuthorBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public AuthorBuilder withApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public AuthorBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public Author build() {
        Author author = new Author();
        author.setUser(user);
        author.setRole(role);
        author.setApproved(approved);
        author.setStatus(status);
        return author;
    }
}
