package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender;

final public class MessageSendingException extends RuntimeException{
    private MessageSendingException(String message) {
        super(message);
    }

    public static MessageSendingException triedSendMessage(String message) {
        return new MessageSendingException("Tried to send message, but failed: " + message);
    }

    public static MessageSendingException triedSendMessageAndResponseIsNotOk(String error) {
        return new MessageSendingException("Tried to send message and response is not ok: " + error);
    }
}
