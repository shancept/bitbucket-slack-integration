package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeleted;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ChannelArchiverInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
final public class PRDeletedHandler {
    public static final String RED = "#FF0000";
    private ChannelRepository channelRepository;
    private ChannelArchiverInterface channelArchiver;
    private MessageSenderInterface messageSender;

    public void handle(PRDeletedCommand command) {
        Channel channel = channelRepository.getChannel(command.pullRequestId());
        messageSender.send(command.message(), RED, new ChannelId(channel.getId()));
        channelArchiver.archive(new ChannelId(channel.getId()));
        channelRepository.removeChannel(channel);
    }
}
