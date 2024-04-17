package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Project {
    private String key;
    private int id;
    private String name;
    private Boolean isPublic;
    private String type;
    private Links links;
}
