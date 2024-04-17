package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.http.bitbucketClient;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
abstract public class AbstractRequestSender<T> {
    private final RestTemplate restTemplate;
    private final String apiToken;

    public ResponseEntity<T> createRequest(Class<T> type) {
        RequestEntity<?> requestEntity = prepareRequestEntity();
        return restTemplate.exchange(requestEntity, type);
    }

    protected abstract RequestEntity<?> prepareRequestEntity();

    protected HttpHeaders getHeaders() {
        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiToken);
        return headers;
    }
}
