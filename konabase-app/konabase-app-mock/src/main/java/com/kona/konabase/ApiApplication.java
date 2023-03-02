package com.kona.konabase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
public class ApiApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplicationBuilder()
        .sources(ApiApplication.class)
        .listeners(new ApplicationPidFileWriter("./this.pid")) // stop.sh 사용
        .build();
    application.run(args);
  }

  @PreDestroy
  public void destroy() {
    log.error("shutdown application");
  }
}