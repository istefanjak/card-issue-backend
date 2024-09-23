package com.istefanjak.card.issue.api.config;

import com.istefanjak.card.issue.api.common.Properties;
import com.istefanjak.card.issue.integration.client.CardClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Main application configuration */
@Configuration
public class ApiConfig implements WebMvcConfigurer {
  private final Properties properties;

  public ApiConfig(Properties properties) {
    this.properties = properties;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    if (!CollectionUtils.isEmpty(properties.corsOrigins())) {
      var corsOrigins = properties.corsOrigins().toArray(new String[0]);
      registry
          .addMapping("/**")
          .allowedOrigins(corsOrigins)
          .allowedMethods(CorsConfiguration.ALL)
          .exposedHeaders("Content-Disposition")
          .allowCredentials(true);
    }
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /** Returns {@link CardClient} Bean responsible for contacting the external card service API */
  @Bean
  public CardClient cardClient(RestTemplate restTemplate) {
    return new CardClient(restTemplate, properties.cardServiceUrl());
  }
}
