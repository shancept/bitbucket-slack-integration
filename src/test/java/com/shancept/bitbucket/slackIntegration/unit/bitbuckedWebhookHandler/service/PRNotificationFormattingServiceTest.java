package com.shancept.bitbucket.slackIntegration.unit.bitbuckedWebhookHandler.service;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.EditingTypeEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.*;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.service.PRNotificationFormattingService;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.*;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PRNotificationFormattingServiceTest {

    @org.junit.jupiter.api.Test
    void getChanelName() {
        var PROpenedEvent = new PROpenedEventBuilder().build();

        var result = PRNotificationFormattingService.getChanelName(PROpenedEvent);
        var expected = "pr-" + ProjectBuilder.KEY + "-" + RepositoryBuilder.NAME + "-" + RepositoryBuilder.ID + "-" + PullRequestBuilder.ID;

        assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    void getTopic() {
        var PROpenedEvent = new PROpenedEventBuilder().build();

        var result = PRNotificationFormattingService.getTopic(PROpenedEvent);
        var expected = ":git: Pull request: " + PullRequestBuilder.TITLE + " | :get-branch: to branch: " + RefBuilder.DISPLAY_ID;

        assertEquals(expected, result);
    }

    private static Stream<Arguments> messageTestArguments() {
        var prCommentAddedEvent = new PRCommentAddedEventBuilder().withCommentParentId(null).build();
        var prRepliedCommentAddedEvent = new PRCommentAddedEventBuilder().build();
        var prFromRefUpdatedEventBuilder = new PRFromRefUpdatedEventBuilder().build();
        return Stream.of(
                Arguments.of(
                        new PROpenedEventBuilder().build(),
                        "The pull request was opened by " + ActorBuilder.DISPLAY_NAME + ". Please <" + LinksBuilder.LinkBuilder.HREF + "|review the PR>."
                ),
                Arguments.of(
                        new PRCommentDeletedEventBuilder().build(),
                        "The comment was deleted by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentAddedEvent,
                        ActorBuilder.DISPLAY_NAME
                                + " <" + PRNotificationFormattingService.getLinkToComment(prCommentAddedEvent)
                                + "|commented>: " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prRepliedCommentAddedEvent,
                        ActorBuilder.DISPLAY_NAME
                                + " <" + PRNotificationFormattingService.getLinkToComment(prRepliedCommentAddedEvent)
                                + "|replied to the message>: " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prFromRefUpdatedEventBuilder,
                        "A <" + PRNotificationFormattingService.getLinkToCommit(prFromRefUpdatedEventBuilder) + "|new commit> was added to the pull request by " + ActorBuilder.DISPLAY_NAME + ". \n Commit message: %s \n Please <" + LinksBuilder.LinkBuilder.HREF + "|review the PR>."
                )
        );
    }

    @ParameterizedTest
    @MethodSource("messageTestArguments")
    void getMessage(AbstractEvent event, String expected) {
        String result;
        if (event instanceof PROpenedEvent) {
            result = PRNotificationFormattingService.getMessage((PROpenedEvent) event);
        } else if (event instanceof PRCommentAddedEvent) {
            result = PRNotificationFormattingService.getMessage((PRCommentAddedEvent) event);
        } else if (event instanceof PRCommentDeletedEvent) {
            result = PRNotificationFormattingService.getMessage((PRCommentDeletedEvent) event);
        } else if (event instanceof PRFromRefUpdatedEvent) {
            var commitMessage = "commitMessage";
            result = PRNotificationFormattingService.getMessage((PRFromRefUpdatedEvent) event, commitMessage);
            expected = String.format(expected, commitMessage);
        } else {
            throw new IllegalArgumentException("Unsupported event type: " + event.getClass().getName());
        }

        assertEquals(expected, result);
    }

    private static Stream<Arguments> messageByPRCommentEditedEventTestArguments() {
        var prCommentEditedEventBuilder = new PRCommentEditedEventBuilder();
        return Stream.of(
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.CHANGED_TEXT,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|comment> was edited by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.CHANGED_TO_TASK,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|comment> was changed to the task by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.CHANGED_TO_COMMENT,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|task> was changed to the comment by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.TASK_RESOLVED,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|task> was resolved by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.TASK_OPENED,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|task> was opened by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.THREAD_RESOLVED,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|thread> was resolved by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                ),
                Arguments.of(
                        prCommentEditedEventBuilder.build(),
                        EditingTypeEnum.THREAD_REOPENED,
                        "The <" + PRNotificationFormattingService.getLinkToComment(prCommentEditedEventBuilder.build()) + "|thread> was reopened by " + ActorBuilder.DISPLAY_NAME + ": " + CommentBuilder.TEXT
                )
        );
    }

    @ParameterizedTest
    @MethodSource("messageByPRCommentEditedEventTestArguments")
    void getMessageByPRCommentEditedEvent(PRCommentEditedEvent prCommentEditedEvent, EditingTypeEnum editingType, String expected) {
        var result = PRNotificationFormattingService.getMessage(prCommentEditedEvent, editingType);
        assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    void getLinkToComment() {
        var prCommentAddedEvent = new PRCommentAddedEventBuilder().build();
        var result = PRNotificationFormattingService.getLinkToComment(prCommentAddedEvent);
        var expected = LinksBuilder.LinkBuilder.HREF + "/overview?commentId=" + CommentBuilder.ID;
        assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    void getLinkToCommit() {
        var prFromRefUpdatedEvent = new PRFromRefUpdatedEventBuilder().build();
        var result = PRNotificationFormattingService.getLinkToCommit(prFromRefUpdatedEvent);
        var expected = LinksBuilder.LinkBuilder.HREF + "/commits/" + RefBuilder.LATEST_COMMIT;
        assertEquals(expected, result);
    }

}