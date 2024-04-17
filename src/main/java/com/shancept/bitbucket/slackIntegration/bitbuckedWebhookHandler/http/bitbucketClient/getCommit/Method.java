package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.http.bitbucketClient.getCommit;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.http.bitbucketClient.AbstractRequestSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
final public class Method extends AbstractRequestSender<CommitResponse> {
    private static final String URL = "/projects/%s/repos/%s/commits/%s";

    private final String bitbucketApiUrl;
    private String projectKey;
    private String repositorySlug;
    private String commitId;

    public Method(
            RestTemplate restTemplate,
            @Value("${bitbucket.api.token}") String apiToken,
            @Value("${bitbucket.api.url}") String bitbucketApiUrl

    ) {
        super(restTemplate, apiToken);
        this.bitbucketApiUrl = bitbucketApiUrl;
    }

    public Method withProjectKey(String projectKey) {
        this.projectKey = projectKey;
        return this;
    }

    public Method withRepositorySlug(String repositorySlug) {
        this.repositorySlug = repositorySlug;
        return this;
    }

    public Method withCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    @Override
    public RequestEntity<?> prepareRequestEntity() {
        String url = String.format(bitbucketApiUrl + URL, projectKey, repositorySlug, commitId);
        return new RequestEntity<>(getHeaders(), HttpMethod.GET, URI.create(url));
    }
}
