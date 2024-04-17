package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.rest.resource;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.AbstractEvent;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.StrategyManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
final public class WebhookController {
    private final StrategyManager strategyManager;

    @PostMapping("/webhook/bitbucket")
    public ResponseEntity<Void> webhookHandlerAction(@RequestBody AbstractEvent event) {
        log.debug("Event being processed:" + event);
        strategyManager.getStrategy(event.getEventKey()).handle(event);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
