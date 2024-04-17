package com.shancept.bitbucket.slackIntegration.slackNotifier.config;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackNotifierConfig {
    @Bean
    public MethodsClient methodsClient(@Value("${slack.token}")String token) {
        return Slack.getInstance().methods(token);
    }

}
