package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventKeyEnum {
    PROpened(AbstractEvent.EVENT_KEY_PR_OPENED),
    PRDeclined(AbstractEvent.EVENT_KEY_PR_DECLINED),
    PRFromRefUpdated(AbstractEvent.EVENT_KEY_PR_FROM_REF_UPDATED),
    PRToRefUpdated(AbstractEvent.EVENT_KEY_PR_TO_REF_UPDATED),
    PRReviewerUpdated(AbstractEvent.EVENT_KEY_PR_REVIEWER_UPDATED),
    PRReviewerApproved(AbstractEvent.EVENT_KEY_PR_REVIEWER_APPROVED),
    PRReviewerUnapproved(AbstractEvent.EVENT_KEY_PR_REVIEWER_UNAPPROVED),
    PRReviewerNeedsWork(AbstractEvent.EVENT_KEY_PR_REVIEWER_NEEDS_WORK),
    PRMerged(AbstractEvent.EVENT_KEY_PR_MERGED),
    PRDeleted(AbstractEvent.EVENT_KEY_PR_DELETED),
    PRCommentAdded(AbstractEvent.EVENT_KEY_PR_COMMENT_ADDED),
    PRCommentEdited(AbstractEvent.EVENT_KEY_PR_COMMENT_EDITED),
    PRCommentDeleted(AbstractEvent.EVENT_KEY_PR_COMMENT_DELETED);

    private final String eventKey;
}
