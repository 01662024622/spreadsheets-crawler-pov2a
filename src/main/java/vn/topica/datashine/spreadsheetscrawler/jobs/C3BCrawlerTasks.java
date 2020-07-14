package vn.topica.datashine.spreadsheetscrawler.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.topica.datashine.spreadsheetscrawler.constants.C3BConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.C3BService;

@Component
@Slf4j
public class C3BCrawlerTasks {

  @Autowired
  private C3BService c3BService;

  @Value("${c3b.driveid.thuong}")
  private String c3bDriveIdThuong;

  @Value("${c3b.driveid.vip}")
  private String c3bDriveIdVip;

  @Value("${c3b.driveid.d500}")
  private String c3bDriveIdD500;

  @Value("${c3b.driveid.cs18}")
  private String c3bDriveIdCS18;

  @Value("${c3b.driveid.duan}")
  private String c3bDriveIdDuan;

  @Scheduled(cron = "${c3b.crawler.cron.daily}")
  public void importData(){
    log.info("C3B: Importing C3B Data");
    importDataFromFolderThuong();
    importDataFromFolderVip();
    importDataFromFolderCS18();
    importDataFromFolderD500();
    importDataFromFolderDuan();
    log.info("C3B: Crawled c3b data");
  }

  private void importDataFromFolderThuong() {
    log.info("C3B: Importing c3b data thuong");
    try {
      c3BService.importData(c3bDriveIdThuong, C3BConstants.SHEETS_DATA);
      log.info("C3B: Crawled c3b data thuong");
    } catch (Exception e) {
      log.info("C3B: Importing c3b thuong get exception" + e.getMessage());
    }
  }

  private void importDataFromFolderVip() {
    log.info("C3B: Importing c3b data vip");
    try {
      c3BService.importData(c3bDriveIdVip, C3BConstants.SHEETS_DATA);
      log.info("C3B: Crawled c3b data vip");
    } catch (Exception e) {
      log.info("C3B: Importing c3b vip get exception" + e.getMessage());
    }
  }

  private void importDataFromFolderD500() {
    log.info("C3B: Importing c3b data d500");
    try {
      c3BService.importData(c3bDriveIdD500, C3BConstants.SHEETS_DATA);
      log.info("C3B: Crawled c3b data d500");
    } catch (Exception e) {
      log.info("C3B: Importing c3b d500 get exception" + e.getMessage());
    }
  }

  private void importDataFromFolderCS18() {
    log.info("C3B: Importing c3b data cs18");
    try {
      c3BService.importData(c3bDriveIdCS18, C3BConstants.SHEETS_DATA);
      log.info("C3B: Crawled c3b data cs18");
    } catch (Exception e) {
      log.info("C3B: Importing c3b cs18 get exception" + e.getMessage());
    }
  }

  private void importDataFromFolderDuan() {
    log.info("C3B: Importing c3b data du an");
    try {
      c3BService.importData(c3bDriveIdDuan, C3BConstants.SHEETS_DATA);
      log.info("C3B: Crawled c3b data du an");
    } catch (Exception e) {
      log.info("C3B: Importing c3b duan get exception" + e.getMessage());
    }
  }
}
