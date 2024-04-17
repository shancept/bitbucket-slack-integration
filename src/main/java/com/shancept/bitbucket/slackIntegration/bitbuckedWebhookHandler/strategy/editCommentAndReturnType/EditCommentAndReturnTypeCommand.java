package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.editCommentAndReturnType;

public record EditCommentAndReturnTypeCommand(
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
