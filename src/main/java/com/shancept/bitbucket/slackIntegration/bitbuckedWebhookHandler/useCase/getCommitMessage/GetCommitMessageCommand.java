package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.getCommitMessage;

public record GetCommitMessageCommand(String projectKey, String repositorySlug, String commitId) {
}
