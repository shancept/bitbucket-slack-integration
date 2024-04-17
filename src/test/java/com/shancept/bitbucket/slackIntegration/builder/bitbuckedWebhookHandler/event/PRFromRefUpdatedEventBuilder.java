package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRFromRefUpdatedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PROpenedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.ActorBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.PullRequestBuilder;

final public class PRFromRefUpdatedEventBuilder {
    private Actor actor = new ActorBuilder().build();
    private PullRequest pullRequest = new PullRequestBuilder().build();

    public PRFromRefUpdatedEventBuilder withActor(Actor actor) {
        this.actor = actor;
        return this;
    }

    public PRFromRefUpdatedEventBuilder withPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }

    public PRFromRefUpdatedEvent build() {
        var PRFromRefUpdatedEvent = new PRFromRefUpdatedEvent();
        PRFromRefUpdatedEvent.setActor(actor);
        PRFromRefUpdatedEvent.setPullRequest(pullRequest);
        return PRFromRefUpdatedEvent;
    }
}
