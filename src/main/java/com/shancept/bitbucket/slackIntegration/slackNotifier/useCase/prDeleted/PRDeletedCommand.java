package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeleted;

public record PRDeletedCommand(int pullRequestId, String message) {
}
