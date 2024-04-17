package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prOpened;

import java.util.List;

public record PROpenedCommand(
        String channelName,
        int pullRequestId,
        String actorEmail,
        List<String> reviewersEmails,
        String topic,
        String message
) {
}
