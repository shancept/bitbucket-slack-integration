package com.shancept.bitbucket.slackIntegration.slackNotifier.storage;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.PRId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
final public class ChannelStorage {
    private final Map<PRId, Channel> channelMap = new HashMap<>();

    public void addChannel(Channel channel) {
        channelMap.put(channel.getPRId(), channel);
    }

    public Channel getChannel(PRId prId) {
        return channelMap.get(prId);
    }

    public void removeChannel(PRId prId) {
        channelMap.remove(prId);
    }
}
