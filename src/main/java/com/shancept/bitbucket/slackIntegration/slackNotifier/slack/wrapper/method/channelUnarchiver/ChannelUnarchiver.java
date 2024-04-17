package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelUnarchiver;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.conversations.ConversationsUnarchiveResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
final public class ChannelUnarchiver implements ChannelUnarchiverInterface {
    private MethodsClient methodsClient;

    public void unarchive(ChannelId channelId) throws UnarchiveChannelException {
        ConversationsUnarchiveResponse unarchiveResponse;
        log.info("Unarchive channel");
        try {
            unarchiveResponse = methodsClient.conversationsUnarchive(req -> req.channel(channelId.getId()));
        } catch (IOException | SlackApiException e) {
            throw UnarchiveChannelException.triedArchiveChanel(e.getMessage());
        }
        log.debug(unarchiveResponse.toString());
        if (!unarchiveResponse.isOk()) {
            throw UnarchiveChannelException.triedArchiveChanelAndResponseIsNotOk(unarchiveResponse.getError());
        }
    }

}
