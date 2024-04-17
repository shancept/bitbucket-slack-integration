package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Ref {
    private String id;
    private String displayId;
    private String type;
    private String latestCommit;
    private Repository repository;
}
