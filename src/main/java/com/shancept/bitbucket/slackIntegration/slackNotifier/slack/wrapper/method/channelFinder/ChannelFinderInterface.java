package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelFinder;


import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

public interface ChannelFinderInterface {
    ChannelId find(String channelName) throws FindChannelException;
}
