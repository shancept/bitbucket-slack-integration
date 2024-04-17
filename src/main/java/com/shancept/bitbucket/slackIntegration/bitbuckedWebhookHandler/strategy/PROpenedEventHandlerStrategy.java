package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PROpenedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prOpened.PROpenedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prOpened.PROpenedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
final public class PROpenedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PROpenedHandler prOpenedHandler;

    @Override
    public void handle(AbstractEvent event) {
        PROpenedEvent prOpenedEvent = (PROpenedEvent) event;
        List<String> reviewerEmails = new ArrayList<>();
        prOpenedEvent.getPullRequest().getReviewers().forEach(reviewer -> reviewerEmails.add(reviewer.getUser().getEmailAddress()));
        prOpenedHandler.handle(
            new PROpenedCommand(
                PRNotificationFormattingService.getChanelName(prOpenedEvent),
                prOpenedEvent.getPullRequest().getId(),
                prOpenedEvent.getActor().getEmailAddress(),
                reviewerEmails,
                PRNotificationFormattingService.getTopic(prOpenedEvent),
                PRNotificationFormattingService.getMessage(prOpenedEvent)
            )
        );
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PROpened;
    }
}
