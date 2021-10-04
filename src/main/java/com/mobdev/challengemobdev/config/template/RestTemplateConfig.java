package com.mobdev.challengemobdev.config.template;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
public class RestTemplateConfig {

    private static final int TIMEOUT_CONNECT = 4000;
    private static final int TIMEOUT_READ = 4000;

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(TIMEOUT_CONNECT))
                .setReadTimeout(Duration.ofSeconds(TIMEOUT_READ))
                .build();
    }

}
