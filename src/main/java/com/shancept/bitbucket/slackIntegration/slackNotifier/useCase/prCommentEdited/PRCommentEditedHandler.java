package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentEdited;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
final public class PRCommentEditedHandler {
    public static final String PURPLE = "#8E44AD";
    public static final String GREEN = "#2ECC71";

    private ChannelRepository channelRepository;
    private MessageSenderInterface messageSender;

    public CompletableFuture<Void> handle(PRCommentEditedCommand command) {
        Channel channel = channelRepository.getChannel(command.PullRequestId());
        var sendMessageFeature =
                CompletableFuture.runAsync(() -> messageSender.send(
                        command.message(),
                        command.isResolved() ? GREEN : PRCommentEditedHandler.PURPLE,
                        new ChannelId(channel.getId()))
                );
        return CompletableFuture.allOf(sendMessageFeature);
    }
}
