package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Repository {
    private String slug;
    private int id;
    private String name;
    private String scmId;
    private String state;
    private String statusMessage;
    private Boolean forkable;
    private Project project;
    private Boolean isPublic;
    private Boolean archived;
    private Links links;
}
