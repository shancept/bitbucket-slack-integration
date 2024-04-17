package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRCommentDeletedEvent extends AbstractEvent {
    private Actor actor;
    private PullRequest pullRequest;
    private Comment comment;
    private int commentParentId;

    @Override
    public EventKeyEnum getEventKey() {
        return EventKeyEnum.PRCommentDeleted;
    }
}