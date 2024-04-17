package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.usersInviter;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;

import java.util.List;

public interface UsersInviterInterface {
    void invite(List<String> usersEmails, ChannelId channelId) throws InviteUsersException;
}
