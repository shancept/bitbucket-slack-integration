package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;

public interface EventHandlerStrategyInterface {
    void handle(AbstractEvent event);
    EventKeyEnum getEventName();
}
