package com.shancept.bitbucket.slackIntegration.unit.slackNotifier.domain.entity;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.Comment;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.enums.EditingTypeEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Task;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject.Thread;
import com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.domain.entity.CommentBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

    public static Stream<Arguments> editingTypeArguments() throws CloneNotSupportedException {
        var builder = new CommentBuilder();
        return Stream.of(
                Arguments.of(builder.build(), builder.withText("SomeText").build(), EditingTypeEnum.CHANGED_TEXT),
                Arguments.of(builder.build(), builder.build(), EditingTypeEnum.CHANGED_TEXT),
                Arguments.of(builder.build(), builder.withTask(
                        new Task(true, false, "foo")
                ).build(), EditingTypeEnum.CHANGED_TO_TASK),
                Arguments.of(builder.withTask(new Task(true, false, "foo")).build(),
                        builder.withTask(new Task(false, false, "foo")).build(),
                        EditingTypeEnum.CHANGED_TO_COMMENT),
                Arguments.of(builder.withTask(new Task(true, false, "foo")).build(),
                        builder.withTask(new Task(true, true, "foo")).build(),
                        EditingTypeEnum.TASK_RESOLVED),
                Arguments.of(builder.withTask(new Task(true, true, "foo")).build(),
                        builder.withTask(new Task(true, false, "foo")).build(),
                        EditingTypeEnum.TASK_OPENED),
                Arguments.of(builder.build(),
                        builder.withThread(new Thread(true, "foo")).build(),
                        EditingTypeEnum.THREAD_RESOLVED),
                Arguments.of(builder.withThread(new Thread(true, "foo")).build(),
                        builder.withThread(new Thread(false, "foo")).build(),
                        EditingTypeEnum.THREAD_REOPENED)
        );
    }

    @ParameterizedTest
    @MethodSource("editingTypeArguments")
    void getEditingTypeTest(Comment comment, Comment newComment, EditingTypeEnum expected) {
        var result = comment.getEditingType(newComment);
        assertEquals(expected, result);
    }
}
