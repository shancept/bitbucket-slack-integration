package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.http.bitbucketClient.getCommit;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommitResponse {
    private String Id;
    private String message;
}
