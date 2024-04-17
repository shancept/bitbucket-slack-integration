package com.shancept.bitbucket.slackIntegration.unit.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StrategyManagerTest {
    @Autowired
    private StrategyManager strategyManager;

    public static Stream<Arguments> getStrategyArguments() {
        return Stream.of(
                Arguments.of(EventKeyEnum.PRCommentAdded, PRCommentAddedEventHandlerStrategy.class),
                Arguments.of(EventKeyEnum.PRCommentDeleted, PRCommentDeletedEventHandlerStrategy.class),
                Arguments.of(EventKeyEnum.PRCommentEdited, PRCommentEditedEventHandlerStrategy.class),
                Arguments.of(EventKeyEnum.PRDeclined, PRDeclinedEventHandlerStrategy.class),
                Arguments.of(EventKeyEnum.PRDeleted, PRDeletedEventHandlerStrategy.class),
                Arguments.of(EventKeyEnum.PROpened, PROpenedEventHandlerStrategy.class)
        );
    }

    @ParameterizedTest
    @MethodSource("getStrategyArguments")
    void getStrategy(EventKeyEnum eventKey, Class<EventHandlerStrategyInterface> expectedStrategyClass) {
        EventHandlerStrategyInterface strategy = strategyManager.getStrategy(eventKey);
        assertEquals(expectedStrategyClass, strategy.getClass());
    }

}