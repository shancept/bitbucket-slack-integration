package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.EditingTypeEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.*;

final public class PRNotificationFormattingService {
    public static String getChanelName(PROpenedEvent prOpenedEvent) {
        return String.format("pr-%s-%s-%s-%s",
                prOpenedEvent.getPullRequest().getToRef().getRepository().getProject().getKey(),
                prOpenedEvent.getPullRequest().getToRef().getRepository().getName(),
                prOpenedEvent.getPullRequest().getToRef().getRepository().getId(),
                prOpenedEvent.getPullRequest().getId());
    }

    public static String getTopic(PROpenedEvent prOpenedEvent) {
        return String.format(":git: Pull request: %s | :get-branch: to branch: %s",
                prOpenedEvent.getPullRequest().getTitle(),
                prOpenedEvent.getPullRequest().getToRef().getDisplayId()
        );
    }

    public static String getMessage(PROpenedEvent prOpenedEvent) {
        return String.format("The pull request was opened by %s. Please <%s|review the PR>.",
                prOpenedEvent.getActor().getDisplayName(),
                prOpenedEvent.getPullRequest().getLinks().getFirstHref()
        );
    }

    public static String getMessage(PRCommentAddedEvent prCommentAddedEvent) {
        String format;
        if (prCommentAddedEvent.getCommentParentId() == null) {
            format = "%s <%s|commented>: %s";
        } else {
            format = "%s <%s|replied to the message>: %s";
        }
        return String.format(format,
                prCommentAddedEvent.getActor().getDisplayName(),
                PRNotificationFormattingService.getLinkToComment(prCommentAddedEvent),
                prCommentAddedEvent.getComment().getText()
        );
    }

    public static String getMessage(PRDeletedEvent prDeletedEvent) {
        return String.format("The pull request was deleted by %s", prDeletedEvent.getActor().getDisplayName());
    }

    public static String getMessage(PRDeclinedEvent prDeclinedEvent) {
        return String.format("The pull request was declined by %s", prDeclinedEvent.getActor().getDisplayName());
    }

    public static String getMessage(PRCommentEditedEvent prCommentEditedEvent, EditingTypeEnum editingType) {
        return switch (editingType) {
            case CHANGED_TO_TASK -> String.format("The <%s|comment> was changed to the task by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
            case CHANGED_TO_COMMENT -> String.format("The <%s|task> was changed to the comment by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
            case TASK_RESOLVED -> String.format("The <%s|task> was resolved by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
            case TASK_OPENED -> String.format("The <%s|task> was opened by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
            case THREAD_RESOLVED -> String.format("The <%s|thread> was resolved by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
            case THREAD_REOPENED -> String.format("The <%s|thread> was reopened by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
            default -> String.format("The <%s|comment> was edited by %s: %s",
                    getLinkToComment(prCommentEditedEvent),
                    prCommentEditedEvent.getActor().getDisplayName(),
                    prCommentEditedEvent.getComment().getText()
            );
        };
    }

    public static String getMessage(PRCommentDeletedEvent prCommentDeletedEvent) {
        return String.format("The comment was deleted by %s: %s",
                prCommentDeletedEvent.getActor().getDisplayName(),
                prCommentDeletedEvent.getComment().getText()
        );
    }

    public static String getMessage(PRFromRefUpdatedEvent prFromRefUpdatedEvent, String commitMessage) {
        return String.format("A <%s|new commit> was added to the pull request by %s. \n Commit message: %s \n Please <%s|review the PR>.",
                getLinkToCommit(prFromRefUpdatedEvent),
                prFromRefUpdatedEvent.getActor().getDisplayName(),
                commitMessage,
                prFromRefUpdatedEvent.getPullRequest().getLinks().getFirstHref()
        );
    }

    public static String getLinkToComment(PRCommentAddedEvent prCommentAddedEvent) {
        return String.format(
                "%s/overview?commentId=%s",
                prCommentAddedEvent.getPullRequest().getLinks().getFirstHref(),
                prCommentAddedEvent.getComment().getId()
        );
    }

    public static String getLinkToComment(PRCommentEditedEvent prCommentEditedEvent) {
        return String.format(
                "%s/overview?commentId=%s",
                prCommentEditedEvent.getPullRequest().getLinks().getFirstHref(),
                prCommentEditedEvent.getComment().getId()
        );
    }

    public static String getLinkToCommit(PRFromRefUpdatedEvent prFromRefUpdatedEvent) {
        return String.format(
                "%s/commits/%s",
                prFromRefUpdatedEvent.getPullRequest().getLinks().getFirstHref(),
                prFromRefUpdatedEvent.getPullRequest().getFromRef().getLatestCommit()
        );
    }
}
