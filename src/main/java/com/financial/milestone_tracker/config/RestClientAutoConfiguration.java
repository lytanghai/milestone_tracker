package com.financial.milestone_tracker.config;

import com.financial.milestone_tracker.config.properties.RestClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RestClientProperties.class)
public class RestClientAutoConfiguration {
}