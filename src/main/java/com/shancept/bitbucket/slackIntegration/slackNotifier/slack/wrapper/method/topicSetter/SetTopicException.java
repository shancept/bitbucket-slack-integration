package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.topicSetter;

final public class SetTopicException extends RuntimeException{
    private SetTopicException(String message) {
        super(message);
    }

    public static SetTopicException triedSetTopic(String message) {
        return new SetTopicException("Tried to set topic, but failed: " + message);
    }

    public static SetTopicException triedSetTopicAndResponseIsNotOk(String error) {
        return new SetTopicException("Tried to set topic, but response is not ok: " + error);
    }
}
