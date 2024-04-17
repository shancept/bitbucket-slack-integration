package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Properties;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.User;

import java.util.ArrayList;
import java.util.List;

final public class CommentBuilder {
    public final static int ID = 1;
    public final static int VERSION = 1;
    public final static String TEXT = "comment_text";
    public final static long CREATED_DATE = 1L;
    public final static long UPDATED_DATE = 1L;

    private Properties properties = new PropertiesBuilder().build();
    private int id = ID;
    private int version = VERSION;
    private String text = TEXT;
    private User author = new UserBuilder().build();
    private long createdDate = CREATED_DATE;
    private long updatedDate = UPDATED_DATE;
    private List<Comment> comments = new ArrayList<>();

    public CommentBuilder withProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    public CommentBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public CommentBuilder withVersion(int version) {
        this.version = version;
        return this;
    }

    public CommentBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public CommentBuilder withAuthor(User author) {
        this.author = author;
        return this;
    }

    public CommentBuilder withCreatedDate(long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public CommentBuilder withUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public CommentBuilder withComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Comment build() {
        Comment comment = new Comment();
        comment.setProperties(properties);
        comment.setId(id);
        comment.setVersion(version);
        comment.setText(text);
        comment.setAuthor(author);
        comment.setCreatedDate(createdDate);
        comment.setUpdatedDate(updatedDate);
        comment.setComments(comments);
        return comment;
    }
}
