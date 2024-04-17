package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelArchiver;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ArchiveChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ChannelArchiverInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

final public class ChannelArchiverMock implements ChannelArchiverInterface {
    @Getter
    private ChannelId channelId;

    @Override
    public void archive(ChannelId channelId) throws ArchiveChannelException {
        this.channelId = channelId;
    }
}
