package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Links;

final public class ActorBuilder {
    public final static String NAME = "user";
    public final static String EMAIL_ADDRESS = "test@test.com";
    public final static boolean ACTIVE = true;
    public final static String DISPLAY_NAME = "User";
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

    public ActorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ActorBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public ActorBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public ActorBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ActorBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ActorBuilder withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public ActorBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActorBuilder withLinks(Links links) {
        this.links = links;
        return this;
    }

    public Actor build() {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setEmailAddress(emailAddress);
        actor.setActive(active);
        actor.setDisplayName(displayName);
        actor.setId(id);
        actor.setSlug(slug);
        actor.setType(type);
        actor.setLinks(links);
        return actor;
    }
}
