package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelUnarchiver;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

public interface ChannelUnarchiverInterface {
    void unarchive(ChannelId channelId) throws UnarchiveChannelException;
}
