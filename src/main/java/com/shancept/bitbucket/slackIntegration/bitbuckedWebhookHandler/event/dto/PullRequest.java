package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PullRequest {
    private int id;
    private String title;
    private String state;
    private boolean open;
    private boolean closed;
    private long createdDate;
    private long updatedDate;
    private Ref fromRef;
    private Ref toRef;
    private boolean locked;
    private Author author;
    private List<Reviewer> reviewers;
    private List<Participant> participants;
    private Links links;
}
