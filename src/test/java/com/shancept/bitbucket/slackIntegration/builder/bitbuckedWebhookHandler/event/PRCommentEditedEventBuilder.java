package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRCommentEditedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.ActorBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.CommentBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.PullRequestBuilder;

final public class PRCommentEditedEventBuilder {
    public final static int COMMENT_PARENT_ID = 1;
    public final static String PREVIOUS_COMMENT = "previous comment";
    private Actor actor = new ActorBuilder().build();
    private PullRequest pullRequest = new PullRequestBuilder().build();
    private Comment comment = new CommentBuilder().build();
    private Integer commentParentId = COMMENT_PARENT_ID;
    private String previousComment = PREVIOUS_COMMENT;

    public PRCommentEditedEventBuilder withActor(Actor actor) {
        this.actor = actor;
        return this;
    }

    public PRCommentEditedEventBuilder withPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }

    public PRCommentEditedEventBuilder withComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public PRCommentEditedEventBuilder withCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
        return this;
    }

    public PRCommentEditedEvent build() {
        var prCommentEditedEvent = new PRCommentEditedEvent();
        prCommentEditedEvent.setActor(actor);
        prCommentEditedEvent.setPullRequest(pullRequest);
        prCommentEditedEvent.setComment(comment);
        prCommentEditedEvent.setCommentParentId(commentParentId);
        prCommentEditedEvent.setPreviousComment(previousComment);
        return prCommentEditedEvent;
    }
}
