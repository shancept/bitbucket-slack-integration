package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator;

final public class CreatingChannelException extends RuntimeException{
    private CreatingChannelException(String message) {
        super(message);
    }

    public static CreatingChannelException triedCreateChannel(String message) {
        return new CreatingChannelException("Tried to create chanel, but failed: " + message);
    }

    public static CreatingChannelException triedCreateChannelAndResponseIsNotOk(String error) {
        return new CreatingChannelException("Tried to create chanel and response is not ok: " + error);
    }
}
