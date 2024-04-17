package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PROpenedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.ActorBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.PullRequestBuilder;

final public class PROpenedEventBuilder {
    private Actor actor = new ActorBuilder().build();
    private PullRequest pullRequest = new PullRequestBuilder().build();

    public PROpenedEventBuilder withActor(Actor actor) {
        this.actor = actor;
        return this;
    }

    public PROpenedEventBuilder withPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }

    public PROpenedEvent build() {
        var PROpenedEvent = new PROpenedEvent();
        PROpenedEvent.setActor(actor);
        PROpenedEvent.setPullRequest(pullRequest);
        return PROpenedEvent;
    }
}
