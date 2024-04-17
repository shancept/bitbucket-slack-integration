package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.editCommentAndReturnType;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.EditingTypeEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.SeverityEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.StateEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.CommentId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.PRId;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Thread;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
final public class EditCommentAndReturnTypeHandler {
    private CommentRepository commentRepository;

    public EditingTypeEnum handle(EditCommentAndReturnTypeCommand command) {
        var comment = commentRepository.getComment(command.commentId());

        var newComment = new Comment(
                new CommentId(command.commentId()),
                new PRId(command.PRId()),
                command.commentParentId() == null ? null : new CommentId(command.commentParentId()),
                command.version(),
                command.text(),
                new Thread(
                        command.threadIsResolved(),
                        command.threadResolver()
                ),
                SeverityEnum.valueOf(command.severity()),
                StateEnum.valueOf(command.state()),
                command.resolver()
        );

        EditingTypeEnum editingType = comment.getEditingType(newComment);
        commentRepository.saveComment(newComment);
        return editingType;
    }
}
