package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRCommentAddedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.saveComment.SaveCommentCommand;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.saveComment.SaveCommentHandler;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentAdded.PRCommentAddedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentAdded.PRCommentAddedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
final public class PRCommentAddedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PRCommentAddedHandler prCommentAddedHandler;
    private SaveCommentHandler saveCommentHandler;

    @Override
    public void handle(AbstractEvent event) {
        PRCommentAddedEvent prCommentAddedEvent = (PRCommentAddedEvent) event;
        prCommentAddedHandler.handle(new PRCommentAddedCommand(
                prCommentAddedEvent.getPullRequest().getId(),
                PRNotificationFormattingService.getMessage(prCommentAddedEvent)
        ));

        saveCommentHandler.handle(new SaveCommentCommand(
                prCommentAddedEvent.getComment().getId(),
                prCommentAddedEvent.getPullRequest().getId(),
                prCommentAddedEvent.getCommentParentId(),
                prCommentAddedEvent.getComment().getVersion(),
                prCommentAddedEvent.getComment().getText(),
                prCommentAddedEvent.getComment().isThreadResolved(),
                Optional.ofNullable(prCommentAddedEvent.getComment().getThreadResolver())
                        .map(User::getDisplayName).orElse(null),
                prCommentAddedEvent.getComment().getSeverity(),
                prCommentAddedEvent.getComment().getState(),
                Optional.ofNullable(prCommentAddedEvent.getComment().getResolver())
                        .map(User::getDisplayName).orElse(null)
        ));
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PRCommentAdded;
    }
}
