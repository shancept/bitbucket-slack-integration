package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelCreator;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator.ChannelCreatorInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

final public class ChannelCreatorMock implements ChannelCreatorInterface {
    public static final ChannelId CHANEL_ID = new ChannelId("CHANEL_ID");
    @Getter
    private String channelName;

    @Override
    public ChannelId create(String channelName) {
        this.channelName = channelName;
        return CHANEL_ID;
    }
}
