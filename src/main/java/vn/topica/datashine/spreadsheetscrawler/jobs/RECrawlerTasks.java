package vn.topica.datashine.spreadsheetscrawler.jobs;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.topica.datashine.spreadsheetscrawler.constants.REConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.REService;

@Component
@Slf4j
public class RECrawlerTasks {
  
  @Autowired
  private REService reService;

  @Value("${re.driveid.all}")
  private String reDriveId;

  @Scheduled(cron = "${re.crawler.cron.daily}")
  public void scheduledCrawlTodayBlockData() {
    log.info("RE: Crawling today block data");
    try {
      importDataFromFolder();
      log.info("RE: Crawled today block data");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void importDataFromFolder() throws IOException {
    log.info("RE: importing RE data");
    reService.importData(reDriveId, REConstants.SHEETS_DATA);

  }
}
