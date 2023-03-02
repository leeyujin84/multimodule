package com.kona.konabase.core.common.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * http://{host}:{port}/actuator/health
 */
@Component
@PropertySource("classpath:application-build-info.properties")
public class ApplicationHealthIndicator implements HealthIndicator {

  @Value("${info.java.version:UNKNOWN}")
  String javaVersion;
  @Value("${info.git.revision:UNKNOWN}")
  String gitRevision;
  @Value("${info.git.hash:UNKNOWN}")
  String gitHash;
  @Value("${info.git.message:UNKNOWN}")
  String gitMessage;
  @Value("${info.git.branchName:UNKNOWN}")
  String gitBranchName;
  @Value("${info.git.commitDate:UNKNOWN}")
  String gitCommitDate;
  @Value("${info.build.date:UNKNOWN}")
  String buildDate;
  @Value("${info.build.number:UNKNOWN}") //jenkins Build No
  String buildNumber;
  @Value("${application.component.name:UNKNOWN}")
  String componentName;
  @Value("${application.component.code:UNKNOWN}")
  String componentCode;
  @Value("${spring.profiles.active:UNKNOWN}")
  String activeProfile;


  @Override
  public Health health() {
    int errorCode = check();
    if (errorCode != 0) {
      return Health.down().withDetail("Error Code", errorCode).build();
    }

    return Health.up()
        .withDetail("javaVersion", javaVersion)
        .withDetail("gitRevision", gitRevision)
        .withDetail("gitHash", gitHash)
//        .withDetail("gitMessage", gitMessage)
        .withDetail("gitBranchName", gitBranchName)
        .withDetail("gitCommitDate", gitCommitDate)
        .withDetail("buildDate", buildDate)
        .withDetail("buildNumber", buildNumber)
        .withDetail("componentName", componentName)
        .withDetail("componentCode", componentCode)
        .withDetail("activeProfile", activeProfile)

        .build();
  }

  private int check() {
    return 0;
  }

}