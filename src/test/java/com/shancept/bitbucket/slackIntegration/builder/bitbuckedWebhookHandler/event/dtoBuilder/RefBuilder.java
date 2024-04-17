package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Ref;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Repository;

final public class RefBuilder {
    public final static String ID = "ref_id";
    public final static String DISPLAY_ID = "displayId";
    public final static String TYPE = "ref_type";
    public final static String LATEST_COMMIT = "latestCommit";

    private String id = ID;
    private String displayId = DISPLAY_ID;
    private String type = TYPE;
    private String latestCommit = LATEST_COMMIT;
    private Repository repository = new RepositoryBuilder().build();

    public RefBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public RefBuilder withDisplayId(String displayId) {
        this.displayId = displayId;
        return this;
    }

    public RefBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public RefBuilder withLatestCommit(String latestCommit) {
        this.latestCommit = latestCommit;
        return this;
    }

    public RefBuilder withRepository(Repository repository) {
        this.repository = repository;
        return this;
    }

    public Ref build() {
        Ref ref = new Ref();
        ref.setId(id);
        ref.setDisplayId(displayId);
        ref.setType(type);
        ref.setLatestCommit(latestCommit);
        ref.setRepository(repository);
        return ref;
    }
}
