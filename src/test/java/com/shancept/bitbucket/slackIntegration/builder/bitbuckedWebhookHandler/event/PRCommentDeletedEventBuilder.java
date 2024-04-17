package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRCommentDeletedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.ActorBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.CommentBuilder;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.PullRequestBuilder;

final public class PRCommentDeletedEventBuilder {
    public final static int COMMENT_PARENT_ID = 1;
    private Actor actor = new ActorBuilder().build();
    private PullRequest pullRequest = new PullRequestBuilder().build();
    private Comment comment = new CommentBuilder().build();
    private int commentParentId = COMMENT_PARENT_ID;

    public PRCommentDeletedEventBuilder withActor(Actor actor) {
        this.actor = actor;
        return this;
    }

    public PRCommentDeletedEventBuilder withPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }

    public PRCommentDeletedEventBuilder withComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public PRCommentDeletedEventBuilder withCommentParentId(int commentParentId) {
        this.commentParentId = commentParentId;
        return this;
    }

    public PRCommentDeletedEvent build() {
        PRCommentDeletedEvent prCommentDeletedEvent = new PRCommentDeletedEvent();
        prCommentDeletedEvent.setActor(actor);
        prCommentDeletedEvent.setPullRequest(pullRequest);
        prCommentDeletedEvent.setComment(comment);
        prCommentDeletedEvent.setCommentParentId(commentParentId);
        return prCommentDeletedEvent;
    }
}
