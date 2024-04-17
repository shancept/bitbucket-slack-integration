package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Links;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;

final public class UserBuilder {
    public final static String NAME = "user_name";
    public final static String EMAIL_ADDRESS = "user_email";
    public final static boolean ACTIVE = true;
    public final static String DISPLAY_NAME = "user_display_name";
    public final static int ID = 1;
    public final static String SLUG = "user_slug";
    public final static String TYPE = "user_type";

    private String name = NAME;
    private String emailAddress = EMAIL_ADDRESS;
    private boolean active = ACTIVE;
    private String displayName = DISPLAY_NAME;
    private int id = ID;
    private String slug = SLUG;
    private String type = TYPE;
    private Links links = new LinksBuilder().build();

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public UserBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public UserBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public UserBuilder withLinks(Links links) {
        this.links = links;
        return this;
    }

    public User build() {
        User user = new User();
        user.setName(name);
        user.setEmailAddress(emailAddress);
        user.setActive(active);
        user.setDisplayName(displayName);
        user.setId(id);
        user.setSlug(slug);
        user.setType(type);
        user.setLinks(links);
        return user;
    }
}
