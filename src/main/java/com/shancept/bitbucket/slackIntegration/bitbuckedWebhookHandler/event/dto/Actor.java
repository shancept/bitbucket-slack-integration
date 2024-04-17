package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor {
    private String name;
    private String emailAddress;
    private boolean active;
    private String displayName;
    private int id;
    private String slug;
    private String type;
    private Links links;
}