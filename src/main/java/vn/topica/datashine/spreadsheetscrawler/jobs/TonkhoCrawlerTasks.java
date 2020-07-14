package vn.topica.datashine.spreadsheetscrawler.jobs;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.topica.datashine.spreadsheetscrawler.constants.TonKhoConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.TonKhoService;

@Component
@Slf4j
public class TonkhoCrawlerTasks {

  @Autowired
  private TonKhoService tonKhoService;

  @Value("${tonkho.driveid.all}")
  private String tonkhoDriveId;

  @Scheduled(cron = "${tonkho.crawler.cron.daily}")
  public void scheduledCrawlTodayBlockData() {
    log.info("Ton kho: Crawling today block data");
    try {
      importDataFromFolder();
      log.info("Ton kho: Crawled today block data");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void importDataFromFolder() throws IOException {
    log.info("Ton kho: importing RE data");
    tonKhoService.importData(tonkhoDriveId, TonKhoConstants.SHEETS_DATA);
  }
}
