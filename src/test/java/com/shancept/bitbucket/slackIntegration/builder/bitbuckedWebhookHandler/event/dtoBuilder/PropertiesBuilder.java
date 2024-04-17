package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Properties;

public class PropertiesBuilder {
    public final static int REPOSITORY_ID = 1;

    private int repositoryId = REPOSITORY_ID;

    public PropertiesBuilder withRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    public Properties build() {
        Properties properties = new Properties();
        properties.setRepositoryId(repositoryId);
        return properties;
    }
}
