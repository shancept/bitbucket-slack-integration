package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
final public  class Task {
    private Boolean isTask;
    private Boolean isTaskResolved;
    private String taskResolver;
}
