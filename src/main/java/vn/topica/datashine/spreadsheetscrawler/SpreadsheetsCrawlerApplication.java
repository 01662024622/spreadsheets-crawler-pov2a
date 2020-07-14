package vn.topica.datashine.spreadsheetscrawler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vn.topica.datashine.spreadsheetscrawler.config.SchedulingConfigurerConfiguration;

@SpringBootApplication
@EntityScan(basePackages = {"vn.topica.datashine.spreadsheetscrawler.models"})
@EnableJpaRepositories(basePackages = {"vn.topica.datashine.spreadsheetscrawler.repository"})
@EnableTransactionManagement
@Slf4j
@EnableScheduling
public class SpreadsheetsCrawlerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpreadsheetsCrawlerApplication.class, args);
  }
}

