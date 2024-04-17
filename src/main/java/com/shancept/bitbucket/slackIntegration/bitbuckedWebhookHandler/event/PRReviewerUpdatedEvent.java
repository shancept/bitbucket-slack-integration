package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Actor;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.PullRequest;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Reviewer;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
final public class PRReviewerUpdatedEvent extends AbstractEvent {
    private Actor actor;
    private PullRequest pullRequest;
    private List<Reviewer> addedReviewers;
    private List<Reviewer> removedReviewers;

    @Override
    public EventKeyEnum getEventKey() {
        return EventKeyEnum.PRReviewerUpdated;
    }
}
