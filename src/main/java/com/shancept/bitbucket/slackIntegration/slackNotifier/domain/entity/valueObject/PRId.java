package com.shancept.bitbucket.slackIntegration.slackNotifier.domain.entity.valueObject;

import java.util.Objects;

public record PRId(int id) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PRId prId = (PRId) o;
        return id == prId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
