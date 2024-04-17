package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentDeleted;

public record PRCommentDeletedCommand(int PullRequestId, String message) {
}
