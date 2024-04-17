package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Participant;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;

final public class ParticipantBuilder {
    public final static String PARTICIPATION_STATUS = "participant_status";

    private User user = new UserBuilder().build();
    private String participationStatus = PARTICIPATION_STATUS;

    public ParticipantBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public ParticipantBuilder withParticipationStatus(String participationStatus) {
        this.participationStatus = participationStatus;
        return this;
    }

    public Participant build() {
        Participant participant = new Participant();
        participant.setUser(user);
        participant.setParticipationStatus(participationStatus);
        return participant;
    }
}
