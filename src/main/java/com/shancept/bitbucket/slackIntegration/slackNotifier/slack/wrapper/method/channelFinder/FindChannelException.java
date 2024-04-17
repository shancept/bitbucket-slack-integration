package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelFinder;

final public class FindChannelException extends RuntimeException{
    private FindChannelException(String message) {
        super(message);
    }

    public static FindChannelException triedFindChanel(String message) {
        return new FindChannelException("Tried find channel, but failed: " + message);
    }

    public static FindChannelException channelNotFound(String channelName) {
        return new FindChannelException("Channel not found: " + channelName);
    }

    public static FindChannelException triedFindChanelAndResponseNotOk(String error) {
        return new FindChannelException("Tried find channel and response is not ok: " + error);
    }
}
