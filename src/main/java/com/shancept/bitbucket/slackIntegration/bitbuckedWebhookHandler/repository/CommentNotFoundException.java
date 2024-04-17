package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.repository;

final public class CommentNotFoundException extends RuntimeException {
    private CommentNotFoundException(String message) {
        super(message);
    }

    public static CommentNotFoundException commentNotFound(String commentId) {
        return new CommentNotFoundException("Comment with ID " + commentId + " not found");
    }
}
