package vn.topica.datashine.spreadsheetscrawler.jobs;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.topica.datashine.spreadsheetscrawler.constants.L1BGConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.L1BGService;

@Component
@Slf4j
public class L1BGCrawlerTasks {

  @Autowired
  private L1BGService l1BGService;

  @Value("${l1bg.driveid.thuong}")
  private String l1bgDriveIdThuong;

  @Value("${l1bg.driveid.vip}")
  private String l1bgDriveIdVip;

  @Value("${l1bg.driveid.d500}")
  private String l1bgDriveIdD500;

  @Value("${l1bg.driveid.cs18}")
  private String l1bgDriveIdCS18;

  @Value("${l1bg.driveid.duan}")
  private String l1bgDriveIdDuan;

  @Scheduled(cron = "${l1bg.crawler.cron.daily}")
  public void importData(){
    log.info("L1BG:  Importing L1BG Data");
    importDataFromFolderThuong();
    importDataFromFolderVip();
    importDataFromFolderCS18();
    importDataFromFolderD500();
    importDataFromFolderDuan();
    log.info("L1BG:  Crawled L1BG data");
  }

  private void importDataFromFolderThuong() {
    log.info("L1BG: Importing l1bg data thuong");
    try {
      l1BGService.importData(l1bgDriveIdThuong, L1BGConstants.SHEETS_DATA);
      log.info("L1BG: Crawled l1bg data thuong");
    } catch (Exception e) {
      log.info("L1BG: Importing l1bg data thuong get exception: " + e.getMessage());
    }

  }

  private void importDataFromFolderVip() {
    log.info("L1BG: Importing l1bg data vip");
    try {
      l1BGService.importData(l1bgDriveIdVip, L1BGConstants.SHEETS_DATA);
      log.info("L1BG: Crawled l1bg data vip");
    } catch (Exception e) {
      log.info("L1BG: Importing l1bg data vip get exception: " + e.getMessage());
    }
  }

  private void importDataFromFolderD500() {
    log.info("L1BG: Importing l1bg data d500");
    try {
      l1BGService.importData(l1bgDriveIdD500, L1BGConstants.SHEETS_DATA);
      log.info("L1BG: Crawled l1bg data d500");
    } catch (Exception e) {
      log.info("L1BG: Importing l1bg data d500 get exception: " + e.getMessage());
    }
  }

  private void importDataFromFolderCS18() {
    log.info("L1BG: Importing l1bg data cs18");
    try {
      l1BGService.importData(l1bgDriveIdCS18, L1BGConstants.SHEETS_DATA);
      log.info("L1BG: Crawled l1bg data cs18");
    } catch (Exception e) {
      log.info("L1BG: Importing l1bg data cs18 get exception: " + e.getMessage());
    }
  }

  private void importDataFromFolderDuan() {
    log.info("L1BG: Importing l1bg data du an");
    try {
      l1BGService.importData(l1bgDriveIdDuan, L1BGConstants.SHEETS_DATA);
      log.info("L1BG: Crawled l1bg data du an");
    } catch (Exception e) {
      log.info("L1BG: Importing l1bg data du an get exception: " + e.getMessage());
    }
  }
}
