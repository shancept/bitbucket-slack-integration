package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelFinder;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelFinder.ChannelFinderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelFinder.FindChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

final public class ChannelFinderMock implements ChannelFinderInterface {
    public final static String CHANNEL_ID = "channelId";
    @Getter
    private String channelName;
    @Override
    public ChannelId find(String channelName) throws FindChannelException {
        this.channelName = channelName;
        return new ChannelId(CHANNEL_ID);
    }
}
