package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator;

public class ChannelIsExistException extends RuntimeException{
    private ChannelIsExistException(String message) {
        super(message);
    }

    public static ChannelIsExistException triedCreateChannel(String message) {
        return new ChannelIsExistException("Tried to create channel, but failed: " + message);
    }
}
