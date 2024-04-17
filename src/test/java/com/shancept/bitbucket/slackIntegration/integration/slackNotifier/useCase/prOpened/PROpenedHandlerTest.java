package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.useCase.prOpened;

import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelCreator.ChannelCreatorMock;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelFinder.ChannelFinderMock;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelUnarchiver.ChannelUnarchiverMock;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderMock;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.topicSetter.TopicSetterMock;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.usersInviter.UsersInviterMock;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator.CreatingChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSendingException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.topicSetter.SetTopicException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.usersInviter.InviteUsersException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.storage.ChannelStorage;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prOpened.PROpenedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prOpened.PROpenedHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class PROpenedHandlerTest {
    @org.junit.jupiter.api.Test
    void successTest() {
        var channelCreatorMock = new ChannelCreatorMock();
        var channelFinder = new ChannelFinderMock();
        var channelUnarchiver = new ChannelUnarchiverMock();
        var usersInviterMock = new UsersInviterMock();
        var topicSetterMock = new TopicSetterMock();
        var messageSenderMock = new MessageSenderMock();
        var channelRepository = new ChannelRepository(new ChannelStorage());

        var handler = new PROpenedHandler(
                channelCreatorMock,
                channelFinder,
                channelUnarchiver,
                usersInviterMock,
                topicSetterMock,
                messageSenderMock,
                channelRepository
        );

        var channelName = "channelName";
        var pullRequestId = 1;
        var actorEmail = "actorEmail";
        var reviewerEmail = "reviewersEmail";
        var reviewersEmail = new ArrayList<>(List.of(reviewerEmail));
        var topic = "topic";
        var message = "message";
        handler.handle(new PROpenedCommand(channelName, pullRequestId, actorEmail, reviewersEmail, topic, message)).join();

        assertEquals(channelCreatorMock.getChannelName(), channelName);
        assertEquals(usersInviterMock.getUsersEmails(), new ArrayList<>(List.of(actorEmail, reviewerEmail)));
        assertEquals(topicSetterMock.getTopic(), topic);
        assertEquals(messageSenderMock.getMessage(), message);
        assertEquals(messageSenderMock.getLineColor(), PROpenedHandler.LINE_COLOR);

        var channel = channelRepository.getChannel(pullRequestId);

        assertEquals(ChannelCreatorMock.CHANEL_ID.getId(), channel.getId());
    }

    @org.junit.jupiter.api.Test
    void channelNotCreatedTest() {
        var handler = new PROpenedHandler(
                channelName -> {
                    throw CreatingChannelException.triedCreateChannel("testError");
                },
                new ChannelFinderMock(),
                new ChannelUnarchiverMock(),
                new UsersInviterMock(),
                new TopicSetterMock(),
                new MessageSenderMock(),
                new ChannelRepository(new ChannelStorage())
        );

        assertThrows(
                CreatingChannelException.class,
                () -> handler.handle(
                        new PROpenedCommand(
                                "channelName",
                                1,
                                "actorEmail",
                                new ArrayList<>(),
                                "topic",
                                "message")
                )
        );
    }

    @org.junit.jupiter.api.Test
    void usersNotInvitedTest() {
        var handler = new PROpenedHandler(
                new ChannelCreatorMock(),
                new ChannelFinderMock(),
                new ChannelUnarchiverMock(),
                (usersEmails, channelId) -> {
                    throw InviteUsersException.triedInviteUsers("testError");
                },
                new TopicSetterMock(),
                new MessageSenderMock(),
                new ChannelRepository(new ChannelStorage())
        );

        Throwable thrown = assertThrows(
                CompletionException.class,
                () -> handler.handle(
                        new PROpenedCommand(
                                "channelName",
                                1,
                                "actorEmail",
                                new ArrayList<>(),
                                "topic",
                                "message")
                ).join()
        ).getCause();
        assertTrue(thrown instanceof InviteUsersException);
    }

    @org.junit.jupiter.api.Test
    void topicNotSetTest() {
        var handler = new PROpenedHandler(
                new ChannelCreatorMock(),
                new ChannelFinderMock(),
                new ChannelUnarchiverMock(),
                new UsersInviterMock(),
                (topic, channelId) -> {
                    throw SetTopicException.triedSetTopic("testError");
                },
                new MessageSenderMock(),
                new ChannelRepository(new ChannelStorage())
        );

        Throwable thrown = assertThrows(
                CompletionException.class,
                () -> handler.handle(
                        new PROpenedCommand(
                                "channelName",
                                1,
                                "actorEmail",
                                new ArrayList<>(),
                                "topic",
                                "message")
                ).join()
        ).getCause();

        assertTrue(thrown instanceof SetTopicException);
    }

    @org.junit.jupiter.api.Test
    void messageNotSentTest() {
        var handler = new PROpenedHandler(
                new ChannelCreatorMock(),
                new ChannelFinderMock(),
                new ChannelUnarchiverMock(),
                new UsersInviterMock(),
                new TopicSetterMock(),
                (message, lineColor, channelId) -> {
                    throw MessageSendingException.triedSendMessage("testError");
                },
                new ChannelRepository(new ChannelStorage())
        );

        Throwable thrown = assertThrows(
                CompletionException.class,
                () -> handler.handle(
                        new PROpenedCommand(
                                "channelName",
                                1,
                                "actorEmail",
                                new ArrayList<>(),
                                "topic",
                                "message")
                ).join()
        ).getCause();

        assertTrue(thrown instanceof MessageSendingException);
    }
}