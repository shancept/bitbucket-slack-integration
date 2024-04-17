package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentEdited;

public record PRCommentEditedCommand(int PullRequestId, String message, Boolean isResolved) {
}
