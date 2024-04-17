package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.useCase.getCommitMessage;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.http.bitbucketClient.getCommit.CommitResponse;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.http.bitbucketClient.getCommit.Method;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
final public class GetCommitMessageHandler {
    private Method getCommitMethod;

    public String handle(GetCommitMessageCommand command) {
        var response = getCommitMethod.withProjectKey(command.projectKey())
                .withRepositorySlug(command.repositorySlug())
                .withCommitId(command.commitId())
                .createRequest(CommitResponse.class);
        return Objects.requireNonNull(response.getBody()).getMessage();
    }
}
