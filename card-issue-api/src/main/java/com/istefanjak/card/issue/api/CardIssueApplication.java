package com.istefanjak.card.issue.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan(
    basePackages = {
      "com.istefanjak.card.issue.api",
      "com.istefanjak.card.issue.core",
      "com.istefanjak.card.issue.integration"
    })
public class CardIssueApplication {

  public static void main(String[] args) {
    SpringApplication.run(CardIssueApplication.class, args);
  }
}
