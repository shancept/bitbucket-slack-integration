package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

public interface ChannelArchiverInterface {
    void archive(ChannelId channelId) throws ArchiveChannelException;
}
