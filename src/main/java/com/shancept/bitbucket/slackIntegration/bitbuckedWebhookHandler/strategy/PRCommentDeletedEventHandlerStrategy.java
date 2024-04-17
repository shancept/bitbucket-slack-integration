package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRCommentDeletedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentDeleted.PRCommentDeletedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentDeleted.PRCommentDeletedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
final public class PRCommentDeletedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PRCommentDeletedHandler prCommentDeletedHandler;

    @Override
    public void handle(AbstractEvent event) {
        PRCommentDeletedEvent prCommentDeletedEvent = (PRCommentDeletedEvent) event;
        prCommentDeletedHandler.handle(new PRCommentDeletedCommand(
                prCommentDeletedEvent.getPullRequest().getId(),
                PRNotificationFormattingService.getMessage(prCommentDeletedEvent)
        ));
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PRCommentDeleted;
    }
}
