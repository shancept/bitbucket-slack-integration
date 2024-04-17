package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRDeclinedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeclined.PRDeclinedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeclined.PRDeclinedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
final public class PRDeclinedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PRDeclinedHandler prDeclinedHandler;

    @Override
    public void handle(AbstractEvent event) {
        PRDeclinedEvent prDeclinedEvent = (PRDeclinedEvent) event;
        prDeclinedHandler.handle(
                new PRDeclinedCommand(
                    prDeclinedEvent.getPullRequest().getId(),
                    PRNotificationFormattingService.getMessage(prDeclinedEvent)
                )
        );
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PRDeclined;
    }
}
