package vn.topica.datashine.spreadsheetscrawler.jobs;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.topica.datashine.spreadsheetscrawler.constants.TICConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.TICService;

@Component
@Slf4j
public class TICCrawlerTasks {

  @Autowired
  private TICService ticService;

  @Value("${tic.driveid.all}")
  private String ticDriveId;

  @Scheduled(cron = "${tic.crawler.cron.daily}")
  public void scheduledCrawlTodayBlockData() {
    log.info("TIC: Crawling today block data");
    try {
      importDataFromFolder();
      log.info("TIC: Crawled today block data");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void importDataFromFolder() throws IOException {
    log.info("TIC: importing TIC data");
    ticService.importData(ticDriveId, TICConstants.SHEETS_DATA);
  }
}
