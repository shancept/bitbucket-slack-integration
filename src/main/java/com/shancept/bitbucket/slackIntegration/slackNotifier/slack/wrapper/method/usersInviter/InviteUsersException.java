package com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.usersInviter;

final public class InviteUsersException extends RuntimeException{
    private InviteUsersException(String message) {
        super(message);
    }

    public static InviteUsersException triedInviteUsers(String message) {
        return new InviteUsersException("Tried to invite users, but failed: " + message);
    }

    public static InviteUsersException triedGetUsers(String message) {
        return new InviteUsersException("Tried to get users, but failed: " + message);
    }

    public static InviteUsersException triedGetUsersAndResponseIsNotOk(String s) {
        return new InviteUsersException("Tried to get users and response is not ok: " + s);
    }
}
