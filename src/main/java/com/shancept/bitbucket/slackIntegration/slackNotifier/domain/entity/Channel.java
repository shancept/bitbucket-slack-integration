package com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.ChannelId;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.ChannelName;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.PRId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
final public class Channel {
    private final ChannelId id;
    private final ChannelName name;
    private final PRId PRId;

    public String getId() {
        return id.id();
    }

    public String getName() {
        return name.name();
    }

    public PRId getPRId() {
        return PRId;
    }
}
