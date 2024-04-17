package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prDeclined;

public record PRDeclinedCommand(int pullRequestId, String message) {
}
