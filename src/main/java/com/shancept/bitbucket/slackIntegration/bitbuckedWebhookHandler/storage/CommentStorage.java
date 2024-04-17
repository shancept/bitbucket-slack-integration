package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.storage;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.CommentId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
final public class CommentStorage {
    private final Map<CommentId, Comment> channelMap = new HashMap<>();

    public void addComment(Comment comment) {
        if (channelMap.containsKey(comment.getId())) {
            channelMap.replace(comment.getId(), comment);
        } else {
            channelMap.put(comment.getId(), comment);
        }
    }

    public Comment getComment(CommentId commentId) {
        return channelMap.get(commentId);
    }
}
