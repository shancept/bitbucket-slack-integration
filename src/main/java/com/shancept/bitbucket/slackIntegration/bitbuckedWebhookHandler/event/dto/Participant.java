package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Participant {
    private User user;
    private String participationStatus;
}
