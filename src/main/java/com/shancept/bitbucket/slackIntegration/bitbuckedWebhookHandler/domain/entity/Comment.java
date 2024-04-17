package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.EditingTypeEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.SeverityEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.StateEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.CommentId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.PRId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Task;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Thread;
import lombok.Getter;

@Getter
final public class Comment {
    private CommentId id;
    private PRId prId;
    private CommentId commentParentId;
    private Integer version;
    private String text;
    private Thread thread;
    private Task task;

    public Comment(
            CommentId id,
            PRId prId,
            CommentId commentParentId,
            Integer version,
            String text,
            Thread thread,
            SeverityEnum severity,
            StateEnum state,
            String resolver
    ) {
        this.id = id;
        this.prId = prId;
        this.commentParentId = commentParentId;
        this.version = version;
        this.text = text;
        this.thread = thread;
        this.task = new Task(
                severity == SeverityEnum.BLOCKER,
                state == StateEnum.RESOLVED,
                resolver
        );
    }

    public Comment(
            CommentId id,
            PRId prId,
            CommentId commentParentId,
            Integer version,
            String text,
            Thread thread,
            Task task
    ) {
        this.id = id;
        this.prId = prId;
        this.commentParentId = commentParentId;
        this.version = version;
        this.text = text;
        this.thread = thread;
        this.task = task;
    }

    public EditingTypeEnum getEditingType(Comment newComment) {
        if (!this.text.equals(newComment.text)) {
            return EditingTypeEnum.CHANGED_TEXT;
        }
        if (!this.task.getIsTask() && newComment.task.getIsTask()) {
            return EditingTypeEnum.CHANGED_TO_TASK;
        }
        if (this.task.getIsTask() && !newComment.task.getIsTask()) {
            return EditingTypeEnum.CHANGED_TO_COMMENT;
        }
        if (this.task.getIsTask() && newComment.task.getIsTask()) {
            if (!this.task.getIsTaskResolved() && newComment.task.getIsTaskResolved()) {
                return EditingTypeEnum.TASK_RESOLVED;
            }
            if (this.task.getIsTaskResolved() && !newComment.task.getIsTaskResolved()) {
                return EditingTypeEnum.TASK_OPENED;
            }
        }
        if (!this.thread.isResolved() && newComment.thread.isResolved()) {
            return EditingTypeEnum.THREAD_RESOLVED;
        }
        if (this.thread.isResolved() && !newComment.thread.isResolved()) {
            return EditingTypeEnum.THREAD_REOPENED;
        }
        return EditingTypeEnum.CHANGED_TEXT;
    }
}
