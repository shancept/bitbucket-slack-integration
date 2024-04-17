package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRCommentEditedEvent extends AbstractEvent {
    private Actor actor;
    private PullRequest pullRequest;
    private Comment comment;
    private int commentParentId;
    private String previousComment;

    @Override
    public EventKeyEnum getEventKey() {
        return EventKeyEnum.PRCommentEdited;
    }
}