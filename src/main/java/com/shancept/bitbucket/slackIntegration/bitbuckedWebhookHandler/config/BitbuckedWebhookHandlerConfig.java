package com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.config;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.filter.RequestLoggingFilter;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.enums.EventKeyEnum;
import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.strategy.EventHandlerStrategyInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BitbuckedWebhookHandlerConfig {
    @Bean
    public Map<EventKeyEnum, EventHandlerStrategyInterface> eventHandlerStrategyMap(
            List<EventHandlerStrategyInterface> eventHandlerStrategies
    ) {
        return eventHandlerStrategies.stream()
                .collect(Collectors.toMap(EventHandlerStrategyInterface::getEventName, Function.identity()));
    }

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter(){
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/webhook/*");
        return registrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
