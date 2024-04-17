package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.channelUnarchiver;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ArchiveChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver.ChannelArchiverInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelUnarchiver.ChannelUnarchiverInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelUnarchiver.UnarchiveChannelException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

final public class ChannelUnarchiverMock implements ChannelUnarchiverInterface {
    @Getter
    private ChannelId channelId;

    @Override
    public void unarchive(ChannelId channelId) throws UnarchiveChannelException {
        this.channelId = channelId;
    }
}
