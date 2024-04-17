package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prFromRefUpdated;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
final public class PRFromRefUpdatedHandler {
    public static final String YELLOW = "#dec000";
    private ChannelRepository channelRepository;
    private MessageSenderInterface messageSender;

    public CompletableFuture<Void> handle(PRFromRefUpdatedCommand command) {
        Channel channel = channelRepository.getChannel(command.PullRequestId());
        var sendMessageFeature =
                CompletableFuture.runAsync(() -> messageSender.send(
                        command.message(),
                        PRFromRefUpdatedHandler.YELLOW,
                        new ChannelId(channel.getId()))
                );
        return CompletableFuture.allOf(sendMessageFeature);
    }
}
