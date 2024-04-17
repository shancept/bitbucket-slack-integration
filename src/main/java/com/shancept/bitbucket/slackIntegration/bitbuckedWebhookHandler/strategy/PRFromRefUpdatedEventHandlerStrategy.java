package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRFromRefUpdatedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.getCommitMessage.GetCommitMessageCommand;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.getCommitMessage.GetCommitMessageHandler;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prFromRefUpdated.PRFromRefUpdatedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prFromRefUpdated.PRFromRefUpdatedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
final public class PRFromRefUpdatedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PRFromRefUpdatedHandler prFromRefUpdatedHandler;
    private GetCommitMessageHandler getCommitMessageHandler;

    @Override
    public void handle(AbstractEvent event) {
        PRFromRefUpdatedEvent prCommentAddedEvent = (PRFromRefUpdatedEvent) event;
        var commitMessage = getCommitMessageHandler.handle(
                new GetCommitMessageCommand(
                        prCommentAddedEvent.getPullRequest().getToRef().getRepository().getProject().getKey(),
                        prCommentAddedEvent.getPullRequest().getToRef().getRepository().getSlug(),
                        prCommentAddedEvent.getPullRequest().getFromRef().getLatestCommit()
                )
        );
        prFromRefUpdatedHandler.handle(new PRFromRefUpdatedCommand(
                prCommentAddedEvent.getPullRequest().getId(),
                PRNotificationFormattingService.getMessage(prCommentAddedEvent, commitMessage)
        ));
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PRFromRefUpdated;
    }
}
