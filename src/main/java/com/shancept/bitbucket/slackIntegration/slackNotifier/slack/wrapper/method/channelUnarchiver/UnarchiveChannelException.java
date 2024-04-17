package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelUnarchiver;

final public class UnarchiveChannelException extends RuntimeException{
    private UnarchiveChannelException(String message) {
        super(message);
    }

    public static UnarchiveChannelException triedArchiveChanel(String message) {
        return new UnarchiveChannelException("Tried to unarchive chanel, but failed: " + message);
    }

    public static UnarchiveChannelException triedArchiveChanelAndResponseIsNotOk(String error) {
        return new UnarchiveChannelException("Tried to unarchive chanel, but response is not ok: " + error);
    }
}
