package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentDeleted;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
final public class PRCommentDeletedHandler {
    public static final String RED = "#F10000";

    private ChannelRepository channelRepository;
    private MessageSenderInterface messageSender;

    public CompletableFuture<Void> handle(PRCommentDeletedCommand command) {
        Channel channel = channelRepository.getChannel(command.PullRequestId());
        var sendMessageFeature =
                CompletableFuture.runAsync(() -> messageSender.send(
                        command.message(),
                        PRCommentDeletedHandler.RED,
                        new ChannelId(channel.getId()))
                );
        return CompletableFuture.allOf(sendMessageFeature);
    }
}
