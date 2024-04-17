package com.shancept.bitbucket.slackIntegration.slackNotifier.useCase.prOpened;

import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.Channel;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.ChannelName;
import com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.PRId;
import com.shancept.bitbucket.slackIntegration.slackNotifier.repository.ChannelRepository;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator.ChannelCreatorInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelCreator.ChannelIsExistException;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelFinder.ChannelFinderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.channelUnarchiver.ChannelUnarchiverInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.messageSender.MessageSenderInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.topicSetter.TopicSetterInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.method.usersInviter.UsersInviterInterface;
import com.shancept.bitbucket.slackIntegration.slackNotifier.slack.wrapper.valueObject.ChannelId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
final public class PROpenedHandler {
    public static final String LINE_COLOR = "#2185CE";
    private ChannelCreatorInterface chanelCreator;
    private ChannelFinderInterface channelFinder;
    private ChannelUnarchiverInterface channelUnarchiver;
    private UsersInviterInterface usersInviter;
    private TopicSetterInterface topicSetter;
    private MessageSenderInterface messageSender;
    private ChannelRepository channelRepository;

    public CompletableFuture<Void> handle(PROpenedCommand command) {
        ChannelId channelId;
        try {
            channelId = chanelCreator.create(command.channelName());
        } catch (ChannelIsExistException e) {
            channelId = channelFinder.find(command.channelName());
            channelUnarchiver.unarchive(channelId);
        }

        channelRepository.addChannel(
            new Channel(
                new com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject.ChannelId(channelId.getId()),
                new ChannelName(command.channelName()),
                new PRId(command.pullRequestId())
            )
        );

        ChannelId channelIdFinal = channelId;

        var messageFuture = CompletableFuture.runAsync(() -> messageSender.send(command.message(), PROpenedHandler.LINE_COLOR, channelIdFinal));
        var inviteFuture = CompletableFuture.runAsync(() -> inviteUsersToChanel(command, channelIdFinal));
        var topicFuture = CompletableFuture.runAsync(() -> topicSetter.set(command.topic(), channelIdFinal));

        return CompletableFuture.allOf(messageFuture, inviteFuture, topicFuture);
    }

    private void inviteUsersToChanel(PROpenedCommand command, ChannelId chanelId) {
        List<String> usersEmailsToInvite = new ArrayList<>();
        usersEmailsToInvite.add(command.actorEmail());
        usersEmailsToInvite.addAll(command.reviewersEmails());
        usersInviter.invite(usersEmailsToInvite, chanelId);
    }
}
