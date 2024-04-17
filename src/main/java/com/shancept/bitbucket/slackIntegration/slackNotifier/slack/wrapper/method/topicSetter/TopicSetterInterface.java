package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.topicSetter;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

public interface TopicSetterInterface {
    void set(String topic, ChannelId channelId) throws SetTopicException;
}
