package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRCommentAddedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.ActorBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.CommentBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.PullRequestBuilder;

final public class PRCommentAddedEventBuilder {
    public final static int COMMENT_PARENT_ID = 1;
    private Actor actor = new ActorBuilder().build();
    private PullRequest pullRequest = new PullRequestBuilder().build();
    private Comment comment = new CommentBuilder().build();
    private Integer commentParentId = COMMENT_PARENT_ID;

    public PRCommentAddedEventBuilder withActor(Actor actor) {
        this.actor = actor;
        return this;
    }

    public PRCommentAddedEventBuilder withPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }

    public PRCommentAddedEventBuilder withComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public PRCommentAddedEventBuilder withCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
        return this;
    }

    public PRCommentAddedEvent build() {
        var prCommentAddedEvent = new PRCommentAddedEvent();
        prCommentAddedEvent.setActor(actor);
        prCommentAddedEvent.setPullRequest(pullRequest);
        prCommentAddedEvent.setComment(comment);
        prCommentAddedEvent.setCommentParentId(commentParentId);
        return prCommentAddedEvent;
    }
}
