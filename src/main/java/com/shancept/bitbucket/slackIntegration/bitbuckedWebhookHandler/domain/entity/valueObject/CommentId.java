package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.domain.entity.valueObject;

import java.util.Objects;

public record CommentId(int id) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return id == commentId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
