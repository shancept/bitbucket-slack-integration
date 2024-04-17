package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.topicSetter;

import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.conversations.ConversationsSetTopicResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
final public class TopicSetter implements TopicSetterInterface {
    private MethodsClient methodsClient;

    public void set(String topic, ChannelId channelId) throws SetTopicException {
        ConversationsSetTopicResponse setTopicResponse;
        log.info("Set topic");
        try {
            setTopicResponse = methodsClient.conversationsSetTopic(req -> req.channel(channelId.getId()).topic(topic));
        } catch (IOException | SlackApiException e) {
            throw SetTopicException.triedSetTopic(e.getMessage());
        }
        log.debug(setTopicResponse.toString());
        if (!setTopicResponse.isOk()) {
            throw SetTopicException.triedSetTopicAndResponseIsNotOk(setTopicResponse.getError());
        }
    }

}
