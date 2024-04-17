package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelFinder;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.conversations.ConversationsListResponse;
import com.slack.api.model.Conversation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
final public class ChannelFinder implements ChannelFinderInterface {
    private MethodsClient methodsClient;

    @Override
    public ChannelId find(String channelName) throws FindChannelException {
        log.info("Find channel");
        final String[] cursor = {null};
        String channelId = null;

        do {
            ConversationsListResponse response;
            try {
                response = methodsClient.conversationsList(req -> req
                        .limit(200)
                        .excludeArchived(false)
                        .cursor(cursor[0]));
            } catch (IOException | SlackApiException e) {
                throw FindChannelException.triedFindChanel(e.getMessage());
            }

            if (!response.isOk()) {
                throw FindChannelException.triedFindChanelAndResponseNotOk(response.getError());
            }

            for (Conversation channel : response.getChannels()) {
                if (channelName.toLowerCase().equals(channel.getName())) {
                    channelId = channel.getId();
                    break;
                }
            }

            cursor[0] = response.getResponseMetadata().getNextCursor();
        } while (channelId == null && cursor[0] != null && !cursor[0].isEmpty());

        if (channelId == null) {
            throw FindChannelException.channelNotFound(channelName.toLowerCase());
        }
        return new ChannelId(channelId);
    }
}
