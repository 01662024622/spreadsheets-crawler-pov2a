package vn.topica.datashine.spreadsheetscrawler.services;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.topica.datashine.spreadsheetscrawler.constants.REConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.REService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestREService {
  @Autowired
  private REService reService;

  private static final String DRIVE_ID_RE = "1VIYFWS7c0zY0k1V8PWci5GE3rsIA9h_N";

  @Test
  public void testImportDataFromFolderThuong() throws IOException {
    reService.importData(DRIVE_ID_RE, REConstants.SHEETS_DATA);
  }
}
