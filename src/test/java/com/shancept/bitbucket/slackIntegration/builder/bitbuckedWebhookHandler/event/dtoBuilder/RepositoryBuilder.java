package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Links;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Project;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Repository;

final public class RepositoryBuilder {
    public final static String SLUG = "repository_slug";
    public final static int ID = 1;
    public final static String NAME = "repository_name";
    public final static String SCM_ID = "scmId";
    public final static String STATE = "repository_state";
    public final static String STATUS_MESSAGE = "statusMessage";
    public final static Boolean FORKABLE = true;
    public final static Boolean IS_PUBLIC = true;
    public final static Boolean ARCHIVED = false;

    private String slug = SLUG;
    private int id = ID;
    private String name = NAME;
    private String scmId = SCM_ID;
    private String state = STATE;
    private String statusMessage = STATUS_MESSAGE;
    private Boolean forkable = FORKABLE;
    private Project project = new ProjectBuilder().build();
    private Boolean isPublic = IS_PUBLIC;
    private Boolean archived = ARCHIVED;
    private Links links = new LinksBuilder().build();

    public RepositoryBuilder withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public RepositoryBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public RepositoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RepositoryBuilder withScmId(String scmId) {
        this.scmId = scmId;
        return this;
    }

    public RepositoryBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public RepositoryBuilder withStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public RepositoryBuilder withForkable(boolean forkable) {
        this.forkable = forkable;
        return this;
    }

    public RepositoryBuilder withProject(Project project) {
        this.project = project;
        return this;
    }

    public RepositoryBuilder withIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public RepositoryBuilder withArchived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public RepositoryBuilder withLinks(Links links) {
        this.links = links;
        return this;
    }

    public Repository build() {
        Repository repository = new Repository();
        repository.setSlug(slug);
        repository.setId(id);
        repository.setName(name);
        repository.setScmId(scmId);
        repository.setState(state);
        repository.setStatusMessage(statusMessage);
        repository.setForkable(forkable);
        repository.setProject(project);
        repository.setIsPublic(isPublic);
        repository.setArchived(archived);
        repository.setLinks(links);
        return repository;
    }
}
