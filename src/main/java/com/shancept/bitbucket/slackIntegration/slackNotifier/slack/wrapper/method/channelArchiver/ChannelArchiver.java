package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.conversations.ConversationsArchiveResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
final public class ChannelArchiver implements ChannelArchiverInterface {
    private MethodsClient methodsClient;

    public void archive(ChannelId channelId) throws ArchiveChannelException {
        ConversationsArchiveResponse archiveResponse;
        log.info("Archive channel");
        try {
            archiveResponse = methodsClient.conversationsArchive(req -> req.channel(channelId.getId()));
        } catch (IOException | SlackApiException e) {
            throw ArchiveChannelException.triedArchiveChanel(e.getMessage());
        }
        log.debug(archiveResponse.toString());
        if (!archiveResponse.isOk()) {
            throw ArchiveChannelException.triedArchiveChanelAndResponseIsNotOk(archiveResponse.getError());
        }
    }

}
