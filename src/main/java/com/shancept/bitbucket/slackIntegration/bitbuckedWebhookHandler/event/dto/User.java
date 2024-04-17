package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String name;
    private String emailAddress;
    private boolean active;
    private String displayName;
    private int id;
    private String slug;
    private String type;
    private Links links;
}
