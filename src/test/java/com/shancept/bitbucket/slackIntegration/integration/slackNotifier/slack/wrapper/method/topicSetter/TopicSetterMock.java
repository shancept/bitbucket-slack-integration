package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.topicSetter;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.topicSetter.TopicSetterInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

final public class TopicSetterMock implements TopicSetterInterface {
    @Getter
    private String topic;
    @Getter
    private ChannelId channelId;

    @Override
    public void set(String topic, ChannelId channelId) {
        this.topic = topic;
        this.channelId = channelId;
    }
}