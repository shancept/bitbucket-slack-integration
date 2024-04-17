package com.shancept.bitbucket.slackIntegration.slackNotifier.repository;

public class ChannelNotFoundException extends RuntimeException {
    private ChannelNotFoundException(String message) {
        super(message);
    }

    public static ChannelNotFoundException channelNotFound(String message) {
        return new ChannelNotFoundException("Channel not found: " + message);
    }
}
