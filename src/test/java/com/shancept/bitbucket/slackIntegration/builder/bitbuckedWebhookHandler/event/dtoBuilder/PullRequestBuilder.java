package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.*;

import java.util.List;

final public class PullRequestBuilder {
    public final static int ID = 1;
    public final static String TITLE = "pull_request_title";
    public final static String STATE = "pull_request_state";
    public final static boolean OPEN = true;
    public final static boolean CLOSED = false;
    public final static long CREATED_DATE = 1L;
    public final static long UPDATED_DATE = 2L;
    public final static boolean LOCKED = false;

    private int id = ID;
    private String title = TITLE;
    private String state = STATE;
    private boolean open = OPEN;
    private boolean closed = CLOSED;
    private long createdDate = CREATED_DATE;
    private long updatedDate = UPDATED_DATE;
    private Ref fromRef = new RefBuilder().build();
    private Ref toRef = new RefBuilder().build();
    private boolean locked = LOCKED;
    private Author author = new AuthorBuilder().build();
    private List<Reviewer> reviewers = List.of(new ReviewerBuilder().build());
    private List<Participant> participants = List.of(new ParticipantBuilder().build());
    private Links links = new LinksBuilder().build();

    public PullRequestBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PullRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PullRequestBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public PullRequestBuilder withOpen(boolean open) {
        this.open = open;
        return this;
    }

    public PullRequestBuilder withClosed(boolean closed) {
        this.closed = closed;
        return this;
    }

    public PullRequestBuilder withCreatedDate(long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public PullRequestBuilder withUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public PullRequestBuilder withFromRef(Ref fromRef) {
        this.fromRef = fromRef;
        return this;
    }

    public PullRequestBuilder withToRef(Ref toRef) {
        this.toRef = toRef;
        return this;
    }

    public PullRequestBuilder withLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public PullRequestBuilder withAuthor(Author author) {
        this.author = author;
        return this;
    }

    public PullRequestBuilder withReviewers(List<Reviewer> reviewers) {
        this.reviewers = reviewers;
        return this;
    }

    public PullRequestBuilder withParticipants(List<Participant> participants) {
        this.participants = participants;
        return this;
    }

    public PullRequestBuilder withLinks(Links links) {
        this.links = links;
        return this;
    }

    public PullRequest build() {
        PullRequest pullRequest = new PullRequest();
        pullRequest.setId(id);
        pullRequest.setTitle(title);
        pullRequest.setState(state);
        pullRequest.setOpen(open);
        pullRequest.setClosed(closed);
        pullRequest.setCreatedDate(createdDate);
        pullRequest.setUpdatedDate(updatedDate);
        pullRequest.setFromRef(fromRef);
        pullRequest.setToRef(toRef);
        pullRequest.setLocked(locked);
        pullRequest.setAuthor(author);
        pullRequest.setReviewers(reviewers);
        pullRequest.setParticipants(participants);
        pullRequest.setLinks(links);
        return pullRequest;
    }
}
