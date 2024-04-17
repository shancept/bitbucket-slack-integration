package com.shancept.bitbucket.slackIntegration.integration.slackNotifier.slack.wrapper.method.usersInviter;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.usersInviter.UsersInviterInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.Getter;

import java.util.List;

final public class UsersInviterMock implements UsersInviterInterface {
    @Getter
    private List<String> usersEmails;

    @Override
    public void invite(List<String> usersEmails, ChannelId channelId) {
        this.usersEmails = usersEmails;
    }
}
