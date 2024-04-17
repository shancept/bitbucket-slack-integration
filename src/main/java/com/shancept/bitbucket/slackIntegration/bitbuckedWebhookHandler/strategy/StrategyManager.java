package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
final public class StrategyManager {
    private final Map<EventKeyEnum, EventHandlerStrategyInterface> eventHandlerStrategyMap;

    public EventHandlerStrategyInterface getStrategy(EventKeyEnum eventKey) {
        EventHandlerStrategyInterface eventHandlerStrategy = eventHandlerStrategyMap.get(eventKey);
        log.info("Selected strategy:" + eventHandlerStrategy.getClass());
        return eventHandlerStrategy;
    }
}
