package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.usersInviter;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.conversations.ConversationsInviteResponse;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
final public class UsersInviter implements UsersInviterInterface {
    private MethodsClient methodsClient;

    public void invite(List<String> usersEmails, ChannelId channelId) throws InviteUsersException {
        ConversationsInviteResponse inviteResponse;
        log.info("Invite users");
        log.debug(usersEmails.toString());
        try {
            inviteResponse = methodsClient.conversationsInvite(req -> req
                    .channel(channelId.getId())
                    .users(getUsersIds(usersEmails)));
        } catch (IOException | SlackApiException e) {
            throw InviteUsersException.triedInviteUsers(e.getMessage());
        }
        log.debug(inviteResponse.toString());
        if (!inviteResponse.isOk()) {
            throw InviteUsersException.triedGetUsersAndResponseIsNotOk(inviteResponse.getError());
        }
    }

    private List<String> getUsersIds(List<String> usersEmails) throws InviteUsersException {
        List<String> usersIds = new ArrayList<>();
        UsersLookupByEmailResponse lookupResponse;
        log.info("Lookup users ByEmail");
        for (String userEmail : usersEmails) {
            try {
                lookupResponse = methodsClient.usersLookupByEmail(req -> req
                        .email(userEmail)
                );
            } catch (IOException | SlackApiException e) {
                throw InviteUsersException.triedGetUsers(e.getMessage());
            }
            log.debug(lookupResponse.toString());
            if (lookupResponse.isOk()) {
                usersIds.add(lookupResponse.getUser().getId());
            } else {
                throw InviteUsersException.triedGetUsersAndResponseIsNotOk(lookupResponse.getError());
            }
        }
        return usersIds;
    }
}
