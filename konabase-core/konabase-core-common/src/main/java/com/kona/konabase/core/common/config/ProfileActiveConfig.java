package com.kona.konabase.core.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@ComponentScan
public class ProfileActiveConfig {

  /**
   * 로컬, 개발 환경 인 경우
   */
  @Slf4j
  @Profile({"local", "default"})
  @Configuration
  public static class CompleteLocalDevelopmentConfiguration {

    public CompleteLocalDevelopmentConfiguration() {
      log.info("create CompleteLocalDevelopmentConfiguration");
    }

    @PostConstruct
    public void init() {
      log.info("init CompleteLocalDevelopmentConfiguration");
    }
  }

  /**
   * 운영, 개발환경 경우
   */
  @Slf4j
  @Profile({"prod", "dev"})
  @Configuration
  public static class ServerStageConfiguration {

    public ServerStageConfiguration() {
      log.info("create ServerStageConfiguration");
    }

    @PostConstruct
    public void init() {
      log.info("init ServerStageConfiguration");
    }

  }

  /**
   * 운영환경
   */
  @Slf4j
  @Profile({"prod"})
  @Configuration
  public static class ProductionConfiguration {

    public ProductionConfiguration() {
      log.info("create ProductionConfiguration");
    }

    @PostConstruct
    public void init() {
      log.info("init ProductionConfiguration");
    }

  }
}
