package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.conversations.ConversationsCreateResponse;
import com.slack.api.model.Conversation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
final public class ChannelCreator implements ChannelCreatorInterface {
    private MethodsClient methodsClient;

    public ChannelId create(String channelName) throws CreatingChannelException, ChannelIsExistException {
        ConversationsCreateResponse conversationsCreateResponse;
        log.info("Create channel");
        log.debug("Channel name: " + channelName);
        try {
            conversationsCreateResponse = methodsClient.conversationsCreate(req -> req
                    .name(channelName.toLowerCase())
                    .isPrivate(false)
            );

        } catch (IOException | SlackApiException e) {
            throw CreatingChannelException.triedCreateChannel(e.getMessage());
        }
        log.debug(conversationsCreateResponse.toString());
        if (conversationsCreateResponse.isOk()) {
            Conversation channel = conversationsCreateResponse.getChannel();
            return new ChannelId(channel.getId());
        } else {
            if (conversationsCreateResponse.getError().equals("name_taken")) {
                throw ChannelIsExistException.triedCreateChannel(conversationsCreateResponse.getError());
            }
            throw CreatingChannelException.triedCreateChannelAndResponseIsNotOk(conversationsCreateResponse.getError());
        }
    }
}
