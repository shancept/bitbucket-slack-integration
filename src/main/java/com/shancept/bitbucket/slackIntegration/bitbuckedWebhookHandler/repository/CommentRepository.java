package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.repository;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.CommentId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.storage.CommentStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
final public class CommentRepository {
    private final CommentStorage commentStorage;

    public void saveComment(Comment comment) {
        commentStorage.addComment(comment);
    }

    public Comment getComment(int commentId) {
        var comment = commentStorage.getComment(new CommentId(commentId));
        if (comment == null) {
            throw CommentNotFoundException.commentNotFound(Integer.toString(commentId));
        }
        return comment;
    }
}
