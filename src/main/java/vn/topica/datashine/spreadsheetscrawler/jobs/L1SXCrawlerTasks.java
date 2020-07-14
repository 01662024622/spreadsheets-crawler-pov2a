package vn.topica.datashine.spreadsheetscrawler.jobs;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.topica.datashine.spreadsheetscrawler.constants.L1SXConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.L1SXService;

@Component
@Slf4j
public class L1SXCrawlerTasks {

  @Autowired
  private L1SXService l1SXService;

  @Value("${l1sx.driveid.thuong}")
  private String l1SXDriveIdThuong;

  @Value("${l1sx.driveid.vip}")
  private String l1SXDriveIdVip;

  @Value("${l1sx.driveid.d500}")
  private String l1SXDriveIdD500;

  @Value("${l1sx.driveid.cs18}")
  private String l1SXDriveIdCs18;

  @Value("${l1sx.driveid.vip}")
  private String l1SXDriveIdDuan;

  @Scheduled(cron = "${l1sx.crawler.cron.daily}")
  public void importData(){
    log.info("L1SX:  Importing L1SX Data");
    importDataFromFolderThuong();
    importDataFromFolderVip();
    importDataFromFolderCS18();
    importDataFromFolderD500();
    importDataFromFolderDuan();
    log.info("L1SX:  Crawled L1SX data");
  }

  private void importDataFromFolderThuong() {
    log.info("L1SX: Importing l1SX data thuong");
    try {
      l1SXService.importData(l1SXDriveIdThuong, L1SXConstants.SHEETS_DATA);
      log.info("L1SX: Crawled l1SX data thuong");
    } catch (Exception e) {
      log.info("L1SX: Importing data from l1sx thuong get exceptions: " + e.getMessage());
    }
  }

  private void importDataFromFolderVip() {
    log.info("L1SX: Importing l1SX data vip");
    try {
      l1SXService.importData(l1SXDriveIdVip, L1SXConstants.SHEETS_DATA);
      log.info("L1SX: Crawled l1SX data vip");
    } catch (Exception e) {
      log.info("L1SX: Importing data from l1sx vip get exceptions: " + e.getMessage());
    }
  }

  private void importDataFromFolderD500() {
    log.info("L1SX: Importing l1SX data d500");
    try {
      l1SXService.importData(l1SXDriveIdD500, L1SXConstants.SHEETS_DATA);
      log.info("L1SX: Crawled l1SX data d500");
    } catch (Exception e) {
      log.info("L1SX: Importing data from l1sx d500 get exceptions: " + e.getMessage());
    }
  }

  private void importDataFromFolderCS18() {
    log.info("L1SX: Importing l1SX data CS18");
    try {
      l1SXService.importData(l1SXDriveIdCs18, L1SXConstants.SHEETS_DATA);
      log.info("L1SX: Crawled l1SX data cs18");
    } catch (Exception e) {
      log.info("L1SX: Importing data from l1sx cs18 get exceptions: " + e.getMessage());
    }
  }

  private void importDataFromFolderDuan() {
    log.info("L1SX: Importing l1SX data du an");
    try {
      l1SXService.importData(l1SXDriveIdDuan, L1SXConstants.SHEETS_DATA);
      log.info("L1SX: Crawled l1SX data du an");
    } catch (Exception e) {
      log.info("L1SX: Importing data from l1sx du an get exceptions: " + e.getMessage());
    }
  }
}
