package com.shancept.bitbucket.slackIntegration.slackNotifier.repository;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.PRId;
import com.shancept.bitbucket.slackIntegration.slackNotifier.storage.ChannelStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
final public class ChannelRepository {
    private ChannelStorage channelMapStorage;

    public void addChannel(Channel channel) {
        channelMapStorage.addChannel(channel);
    }

    public Channel getChannel(int PRId) {
        Channel channel = channelMapStorage.getChannel(new PRId(PRId));
        if (channel == null) {
            throw ChannelNotFoundException.channelNotFound(String.valueOf(PRId));
        }
        return channel;
    }

    public void removeChannel(Channel channel) {
        channelMapStorage.removeChannel(channel.getPRId());
    }
}
