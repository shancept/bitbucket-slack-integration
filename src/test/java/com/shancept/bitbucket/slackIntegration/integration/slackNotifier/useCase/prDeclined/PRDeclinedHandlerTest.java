package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.useCase.prDeclined;

import com.shancept.bitbucket.slackIntegration.builder.slackNotifier.domain.entity.ChannelBuilder;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelArchiver.ChannelArchiverMock;
import com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderMock;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelNotFoundException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ArchiveChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSendingException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.storage.ChannelStorage;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeclined.PRDeclinedCommand;
import com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeclined.PRDeclinedHandler;

import static org.junit.jupiter.api.Assertions.*;

class PRDeclinedHandlerTest {
    @org.junit.jupiter.api.Test
    public void successTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var chanelArchiver = new ChannelArchiverMock();
        var messageSenderMock = new MessageSenderMock();
        var message = "message";

        var handler = new PRDeclinedHandler(channelRepository, chanelArchiver, messageSenderMock);
        var command = new PRDeclinedCommand(pullRequestId, message);
        handler.handle(command);

        assertEquals(message, messageSenderMock.getMessage());
        assertEquals(ChannelBuilder.CHANNEL_ID, messageSenderMock.getChannelId().getId());

        assertThrows(ChannelNotFoundException.class, () -> channelRepository.getChannel(pullRequestId));
    }

    @org.junit.jupiter.api.Test
    void channelNotFoundTest() {
        var pullRequestId = 1;
        var ChannelMapStorage = new ChannelStorage();
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var chanelArchiver = new ChannelArchiverMock();
        var messageSenderMock = new MessageSenderMock();
        var handler = new PRDeclinedHandler(channelRepository, chanelArchiver, messageSenderMock);
        var command = new PRDeclinedCommand(pullRequestId, "message");

        assertThrows(ChannelNotFoundException.class, () -> handler.handle(command));
    }

    @org.junit.jupiter.api.Test
    void messageNotSentTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var chanelArchiver = new ChannelArchiverMock();
        var handler = new PRDeclinedHandler(
                channelRepository,
                chanelArchiver,
                (message, lineColor, channelId) -> {
                    throw MessageSendingException.triedSendMessage("testError");
                });
        var command = new PRDeclinedCommand(pullRequestId, "message");

        assertThrows(MessageSendingException.class, () -> handler.handle(command));
    }

    @org.junit.jupiter.api.Test
    void channelNotArchivedTest() {
        var pullRequestId = ChannelBuilder.PR_ID;
        var ChannelMapStorage = new ChannelStorage();
        ChannelMapStorage.addChannel(new ChannelBuilder().build());
        var channelRepository = new ChannelRepository(ChannelMapStorage);
        var messageSenderMock = new MessageSenderMock();
        var handler = new PRDeclinedHandler(
                channelRepository,
                channelId -> {
                    throw ArchiveChannelException.triedArchiveChanel("testError");
                },
                messageSenderMock);
        var command = new PRDeclinedCommand(pullRequestId, "message");

        assertThrows(ArchiveChannelException.class, () -> handler.handle(command));
    }
}