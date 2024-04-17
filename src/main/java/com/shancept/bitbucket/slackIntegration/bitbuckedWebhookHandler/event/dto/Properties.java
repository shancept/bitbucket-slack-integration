package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Properties {
    private int repositoryId;
}
