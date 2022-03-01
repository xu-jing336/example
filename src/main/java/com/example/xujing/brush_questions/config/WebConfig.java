package com.example.xujing.brush_questions.config;

import com.example.xujing.brush_questions.config.properties.RestProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.net.httpserver.AuthFilter;


@Configuration
public class WebConfig {

    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "auth-open", havingValue = "true", matchIfMissing = true)
    public AuthFilter jwtAuthenticationTokenFilter() {
        return null;
                //new AuthFilter();
    }

}
