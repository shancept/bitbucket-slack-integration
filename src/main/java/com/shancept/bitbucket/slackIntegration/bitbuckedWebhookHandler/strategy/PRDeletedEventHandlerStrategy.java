package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRDeletedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeleted.PRDeletedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeleted.PRDeletedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
final public class PRDeletedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PRDeletedHandler prDeclinedHandler;

    @Override
    public void handle(AbstractEvent event) {
        PRDeletedEvent prDeletedEvent = (PRDeletedEvent) event;
        prDeclinedHandler.handle(
                new PRDeletedCommand(
                    prDeletedEvent.getPullRequest().getId(),
                    PRNotificationFormattingService.getMessage(prDeletedEvent)
                )
        );
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PRDeleted;
    }
}
