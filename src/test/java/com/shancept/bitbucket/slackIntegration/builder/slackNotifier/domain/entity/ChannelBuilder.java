package com.shancept.bitbucket.slackIntegration.builder.slackNotifier.domain.entity;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.ChannelId;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.ChannelName;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.PRId;

final public class ChannelBuilder {
    public final static String CHANNEL_ID = "channelId";
    public final static String CHANNEL_NAME = "channelName";
    public final static int PR_ID = 100;

    private ChannelId id = new ChannelId(CHANNEL_ID);
    private ChannelName name = new ChannelName(CHANNEL_NAME);
    private PRId PRId = new PRId(PR_ID);

    public ChannelBuilder withId(ChannelId id) {
        this.id = id;
        return this;
    }

    public ChannelBuilder withName(ChannelName name) {
        this.name = name;
        return this;
    }

    public ChannelBuilder withPRId(PRId PRId) {
        this.PRId = PRId;
        return this;
    }

    public Channel build() {
        return new Channel(id, name, PRId);
    }
}
