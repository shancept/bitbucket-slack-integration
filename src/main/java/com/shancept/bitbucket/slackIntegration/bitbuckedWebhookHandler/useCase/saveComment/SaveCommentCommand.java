package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.saveComment;

public record SaveCommentCommand(
        int commentId,
        int PRId,
        Integer commentParentId,
        Integer version,
        String text,
        Boolean threadIsResolved,
        String threadResolver,
        String severity,
        String state,
        String resolver
) {
}
