package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.domain.entity;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.CommentId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.PRId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Task;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Thread;
import lombok.Setter;

@Setter
final public class CommentBuilder implements Cloneable {
    public final static int COMMENT_ID = 2;
    public final static int PR_ID = 1;
    public final static int COMMENT_PARENT_ID = 1;
    public final static int VERSION = 1;
    public final static String TEXT = "text";
    public final static Thread THREAD = new Thread(false, "resolver");
    public final static Task TASK = new Task(false, false, "resolver");

    private CommentId id = new CommentId(COMMENT_ID);
    private PRId prId = new PRId(PR_ID);
    private CommentId commentParentId = new CommentId(COMMENT_PARENT_ID);
    private Integer version = VERSION;
    private String text = TEXT;
    private Thread thread = THREAD;
    private Task task = TASK;

    public CommentBuilder withId(CommentId id) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setId(id);
        return clone;
    }

    public CommentBuilder withPrId(PRId prId) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setPrId(prId);
        return clone;
    }

    public CommentBuilder withCommentParentId(CommentId commentParentId) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setCommentParentId(commentParentId);
        return clone;
    }

    public CommentBuilder withVersion(Integer version) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setVersion(version);
        return clone;
    }

    public CommentBuilder withText(String text) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setText(text);
        return clone;
    }

    public CommentBuilder withThread(Thread thread) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setThread(thread);
        return clone;
    }

    public CommentBuilder withTask(Task task) throws CloneNotSupportedException {
        var clone = (CommentBuilder) this.clone();
        clone.setTask(task);
        return clone;
    }

    public Comment build() {
        return new Comment(id, prId, commentParentId, version, text, thread, task);
    }
}
