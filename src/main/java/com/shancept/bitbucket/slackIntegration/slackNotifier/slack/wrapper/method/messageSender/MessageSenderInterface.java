package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

public interface MessageSenderInterface {
    void send(String message, String lineColor, ChannelId channelId) throws MessageSendingException;
}
