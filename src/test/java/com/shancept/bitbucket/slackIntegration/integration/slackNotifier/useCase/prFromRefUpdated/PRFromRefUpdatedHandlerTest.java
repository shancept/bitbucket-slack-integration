package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.useCase.prFromRefUpdated;

import com.shancept.bitbucket.slackIntegration.builder.slackNotifier.domain.entity.ChannelBuilder;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderMock;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelNotFoundException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSendingException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.storage.ChannelStorage;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prFromRefUpdated.PRFromRefUpdatedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prFromRefUpdated.PRFromRefUpdatedHandler;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class PRFromRefUpdatedHandlerTest {
    @org.junit.jupiter.api.Test
    void successTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var messageSenderMock = new MessageSenderMock();
        var handler = new PRFromRefUpdatedHandler(channelRepository, messageSenderMock);
        var message = "message";
        var command = new PRFromRefUpdatedCommand(pullRequestId, message);

        handler.handle(command).join();

        assertEquals(message, messageSenderMock.getMessage());
        assertEquals(PRFromRefUpdatedHandler.YELLOW, messageSenderMock.getLineColor());

        var channel = channelRepository.getChannel(pullRequestId);

        assertEquals(ChannelBuilder.CHANNEL_ID, channel.getId());
    }

    @org.junit.jupiter.api.Test
    void channelNotFoundTest() {
        var pullRequestId = 1;
        var ChannelMapStorage = new ChannelStorage();
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var messageSenderMock = new MessageSenderMock();
        var handler = new PRFromRefUpdatedHandler(channelRepository, messageSenderMock);
        var command = new PRFromRefUpdatedCommand(pullRequestId, "message");

        assertThrows(ChannelNotFoundException.class, () -> handler.handle(command));
    }

    @org.junit.jupiter.api.Test
    void messageNotSentTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var handler = new PRFromRefUpdatedHandler(
                channelRepository,
                (message, lineColor, channelId) -> {
                    throw MessageSendingException.triedSendMessage("testError");
                });
        var command = new PRFromRefUpdatedCommand(pullRequestId, "message");

        Throwable thrown = assertThrows(CompletionException.class, () -> handler.handle(command).join());

        assertTrue(thrown.getCause() instanceof MessageSendingException);
    }
}