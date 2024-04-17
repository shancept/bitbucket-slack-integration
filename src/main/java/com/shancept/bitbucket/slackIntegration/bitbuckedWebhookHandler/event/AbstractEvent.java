package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventKey")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PROpenedEvent.class, name = AbstractEvent.EVENT_KEY_PR_OPENED),
        @JsonSubTypes.Type(value = PRDeclinedEvent.class, name = AbstractEvent.EVENT_KEY_PR_DECLINED),
        @JsonSubTypes.Type(value = PRFromRefUpdatedEvent.class, name = AbstractEvent.EVENT_KEY_PR_FROM_REF_UPDATED),
        @JsonSubTypes.Type(value = PRToRefUpdatedEvent.class, name = AbstractEvent.EVENT_KEY_PR_TO_REF_UPDATED),
        @JsonSubTypes.Type(value = PRReviewerUpdatedEvent.class, name = AbstractEvent.EVENT_KEY_PR_REVIEWER_UPDATED),
        @JsonSubTypes.Type(value = PRReviewerApprovedEvent.class, name = AbstractEvent.EVENT_KEY_PR_REVIEWER_APPROVED),
        @JsonSubTypes.Type(value = PRReviewerUnapprovedEvent.class, name = AbstractEvent.EVENT_KEY_PR_REVIEWER_UNAPPROVED),
        @JsonSubTypes.Type(value = PRReviewerNeedsWorkEvent.class, name = AbstractEvent.EVENT_KEY_PR_REVIEWER_NEEDS_WORK),
        @JsonSubTypes.Type(value = PRMergedEvent.class, name = AbstractEvent.EVENT_KEY_PR_MERGED),
        @JsonSubTypes.Type(value = PRDeletedEvent.class, name = AbstractEvent.EVENT_KEY_PR_DELETED),
        @JsonSubTypes.Type(value = PRCommentAddedEvent.class, name = AbstractEvent.EVENT_KEY_PR_COMMENT_ADDED),
        @JsonSubTypes.Type(value = PRCommentEditedEvent.class, name = AbstractEvent.EVENT_KEY_PR_COMMENT_EDITED),
        @JsonSubTypes.Type(value = PRCommentDeletedEvent.class, name = AbstractEvent.EVENT_KEY_PR_COMMENT_DELETED),
})
@Getter
@Setter
public abstract class AbstractEvent {
    public static final String EVENT_KEY_PR_OPENED = "pr:opened";
    public static final String EVENT_KEY_PR_DECLINED = "pr:declined";
    public static final String EVENT_KEY_PR_FROM_REF_UPDATED = "pr:from_ref_updated";
    public static final String EVENT_KEY_PR_TO_REF_UPDATED = "pr:to_ref_updated";
    public static final String EVENT_KEY_PR_REVIEWER_UPDATED = "pr:reviewer:updated";
    public static final String EVENT_KEY_PR_REVIEWER_APPROVED = "pr:reviewer:approved";
    public static final String EVENT_KEY_PR_REVIEWER_UNAPPROVED = "pr:reviewer:unapproved";
    public static final String EVENT_KEY_PR_REVIEWER_NEEDS_WORK = "pr:reviewer:needs_work";
    public static final String EVENT_KEY_PR_MERGED = "pr:merged";
    public static final String EVENT_KEY_PR_DELETED = "pr:deleted";
    public static final String EVENT_KEY_PR_COMMENT_ADDED = "pr:comment:added";
    public static final String EVENT_KEY_PR_COMMENT_EDITED = "pr:comment:edited";
    public static final String EVENT_KEY_PR_COMMENT_DELETED = "pr:comment:deleted";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    private Date date;

    public abstract EventKeyEnum getEventKey();
}