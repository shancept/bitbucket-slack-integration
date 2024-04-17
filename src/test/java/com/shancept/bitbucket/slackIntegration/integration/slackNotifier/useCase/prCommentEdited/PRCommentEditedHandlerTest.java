package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.useCase.prCommentEdited;

import com.shancept.bitbucket.slackIntegration.builder.slackNotifier.domain.entity.ChannelBuilder;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderMock;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelNotFoundException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSendingException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.storage.ChannelStorage;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentEdited.PRCommentEditedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentEdited.PRCommentEditedHandler;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class PRCommentEditedHandlerTest {
    @org.junit.jupiter.api.Test
    void successTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var messageSenderMock = new MessageSenderMock();
        var handler = new PRCommentEditedHandler(channelRepository, messageSenderMock);
        var message = "message";
        var command = new PRCommentEditedCommand(pullRequestId, message, false);

        handler.handle(command).join();

        assertEquals(message, messageSenderMock.getMessage());
        assertEquals(PRCommentEditedHandler.PURPLE, messageSenderMock.getLineColor());

        var channel = channelRepository.getChannel(pullRequestId);

        assertEquals(ChannelBuilder.CHANNEL_ID, channel.getId());
    }

    @org.junit.jupiter.api.Test
    void channelNotFoundTest() {
        var pullRequestId = 1;
        var ChannelMapStorage = new ChannelStorage();
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var messageSenderMock = new MessageSenderMock();
        var handler = new PRCommentEditedHandler(channelRepository, messageSenderMock);
        var command = new PRCommentEditedCommand(pullRequestId, "message", false);

        assertThrows(ChannelNotFoundException.class, () -> handler.handle(command));
    }

    @org.junit.jupiter.api.Test
    void messageNotSentTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var handler = new PRCommentEditedHandler(
                channelRepository,
                (message, lineColor, channelId) -> {
                    throw MessageSendingException.triedSendMessage("testError");
                });
        var command = new PRCommentEditedCommand(pullRequestId, "message", false);

        Throwable thrown = assertThrows(CompletionException.class, () -> handler.handle(command).join());

        assertTrue(thrown.getCause() instanceof MessageSendingException);
    }
}