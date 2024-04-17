package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Comment {
    private Properties properties;
    private int id;
    private int version;
    private String text;
    private User author;
    private long createdDate;
    private long updatedDate;
    private List<Comment> comments;
    private long resolvedDate;
    private User resolver;
    private boolean threadResolved;
    private User threadResolver;
    private String severity;
    private String state;
}
