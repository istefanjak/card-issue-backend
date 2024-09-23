package com.istefanjak.card.issue.api.common;

import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application properties
 *
 * @param corsOrigins a set of allowed origins for Cross-Origin Resource Sharing (CORS) requests
 * @param cardServiceUrl the URL of the external card service API used by the application
 */
@ConfigurationProperties("app.api")
public record Properties(Set<String> corsOrigins, String cardServiceUrl) {}
