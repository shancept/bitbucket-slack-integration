package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelArchiver;

final public class ArchiveChannelException extends RuntimeException{
    private ArchiveChannelException(String message) {
        super(message);
    }

    public static ArchiveChannelException triedArchiveChanel(String message) {
        return new ArchiveChannelException("Tried to archive chanel, but failed: " + message);
    }

    public static ArchiveChannelException triedArchiveChanelAndResponseIsNotOk(String error) {
        return new ArchiveChannelException("Tried to archive chanel, but response is not ok: " + error);
    }
}
