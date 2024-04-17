package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prFromRefUpdated;

public record PRFromRefUpdatedCommand(int PullRequestId, String message) {

}
