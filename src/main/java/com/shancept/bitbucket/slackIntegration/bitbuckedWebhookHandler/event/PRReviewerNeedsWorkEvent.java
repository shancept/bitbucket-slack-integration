package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Participant;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final public class PRReviewerNeedsWorkEvent extends AbstractEvent {
    private Actor actor;
    private PullRequest pullRequest;
    private Participant participant;
    private String previousStatus;

    @Override
    public EventKeyEnum getEventKey() {
        return EventKeyEnum.PRReviewerNeedsWork;
    }
}
