package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.messageSender;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

final public class MessageSenderMock implements MessageSenderInterface {
    @Getter
    private String message;
    @Getter
    private String lineColor;
    @Getter
    private ChannelId channelId;

    @Override
    public void send(String message, String lineColor, ChannelId channelId) {
        this.message = message;
        this.lineColor = lineColor;
        this.channelId = channelId;
    }
}
