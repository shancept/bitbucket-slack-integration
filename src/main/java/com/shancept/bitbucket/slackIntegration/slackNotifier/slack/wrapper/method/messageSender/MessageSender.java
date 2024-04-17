package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
@AllArgsConstructor
final public class MessageSender implements MessageSenderInterface {
    private MethodsClient methodsClient;

    public void send(String message, String lineColor, ChannelId channelId) throws MessageSendingException {
        ChatPostMessageResponse messageResponse;
        log.info("Send message");
        try {
            messageResponse = methodsClient.chatPostMessage(req -> req
                    .channel(channelId.getId())
                    .attachments(Collections.singletonList(Attachment.builder()
                            .color(lineColor)
                            .text(message)
                            .build()))
            );

        } catch (IOException | SlackApiException e) {
            throw MessageSendingException.triedSendMessage(e.getMessage());
        }
        log.debug(messageResponse.toString());
        if (!messageResponse.isOk()) {
            throw MessageSendingException.triedSendMessageAndResponseIsNotOk(messageResponse.getError());
        }
    }
}
