package com.istefanjak.card.issue.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Configuration file used by the core module. */
@Configuration
@EnableJpaRepositories(basePackages = "com.istefanjak.card.issue.core.repository")
@EntityScan(basePackages = "com.istefanjak.card.issue.core.model")
public class CoreConfig {}
