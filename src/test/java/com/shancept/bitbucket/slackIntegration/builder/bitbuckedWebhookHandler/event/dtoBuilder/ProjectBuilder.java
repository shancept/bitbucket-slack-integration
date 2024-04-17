package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Links;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Project;

final public class ProjectBuilder {
    public final static String KEY = "project_key";
    public final static int ID = 1;
    public final static String NAME = "project_name";
    public final static Boolean IS_PUBLIC = true;
    public final static String TYPE = "project_type";

    private String key = KEY;
    private int id = ID;
    private String name = NAME;
    private Boolean isPublic = IS_PUBLIC;
    private String type = TYPE;
    private Links links = new LinksBuilder().build();

    public ProjectBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public ProjectBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder withIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public ProjectBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ProjectBuilder withLinks(Links links) {
        this.links = links;
        return this;
    }

    public Project build() {
        Project project = new Project();
        project.setKey(key);
        project.setId(id);
        project.setName(name);
        project.setIsPublic(isPublic);
        project.setType(type);
        project.setLinks(links);
        return project;
    }
}
