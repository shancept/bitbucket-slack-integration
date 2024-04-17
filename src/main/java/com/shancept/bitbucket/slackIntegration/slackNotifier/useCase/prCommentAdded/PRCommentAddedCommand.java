package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prCommentAdded;

public record PRCommentAddedCommand(int PullRequestId, String message) {

}
