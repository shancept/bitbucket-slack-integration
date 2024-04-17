package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

public interface ChannelCreatorInterface {
    ChannelId create(String channelName) throws CreatingChannelException, ChannelIsExistException;
}
