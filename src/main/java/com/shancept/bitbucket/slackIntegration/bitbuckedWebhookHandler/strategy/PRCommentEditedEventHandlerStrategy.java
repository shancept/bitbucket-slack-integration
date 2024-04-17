package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.EditingTypeEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.PRCommentEditedEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.editCommentAndReturnType.EditCommentAndReturnTypeCommand;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.editCommentAndReturnType.EditCommentAndReturnTypeHandler;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentEdited.PRCommentEditedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentEdited.PRCommentEditedHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
final public class PRCommentEditedEventHandlerStrategy implements EventHandlerStrategyInterface {
    private PRCommentEditedHandler prCommentEditedHandler;
    private EditCommentAndReturnTypeHandler editCommentAndReturnTypeHandler;

    @Override
    public void handle(AbstractEvent event) {
        PRCommentEditedEvent prCommentEditedEvent = (PRCommentEditedEvent) event;
        var EditingType = editCommentAndReturnTypeHandler.handle(new EditCommentAndReturnTypeCommand(
                prCommentEditedEvent.getComment().getId(),
                prCommentEditedEvent.getPullRequest().getId(),
                prCommentEditedEvent.getCommentParentId(),
                prCommentEditedEvent.getComment().getVersion(),
                prCommentEditedEvent.getComment().getText(),
                prCommentEditedEvent.getComment().isThreadResolved(),
                Optional.ofNullable(prCommentEditedEvent.getComment().getThreadResolver())
                        .map(User::getDisplayName).orElse(null),
                prCommentEditedEvent.getComment().getSeverity(),
                prCommentEditedEvent.getComment().getState(),
                Optional.ofNullable(prCommentEditedEvent.getComment().getResolver())
                        .map(User::getDisplayName).orElse(null)
        ));

        prCommentEditedHandler.handle(new PRCommentEditedCommand(
                prCommentEditedEvent.getPullRequest().getId(),
                PRNotificationFormattingService.getMessage(prCommentEditedEvent, EditingType),
                List.of(EditingTypeEnum.TASK_RESOLVED, EditingTypeEnum.THREAD_RESOLVED).contains(EditingType)
        ));
    }

    @Override
    public EventKeyEnum getEventName() {
        return EventKeyEnum.PRCommentEdited;
    }
}
