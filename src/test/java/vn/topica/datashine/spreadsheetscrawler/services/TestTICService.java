package vn.topica.datashine.spreadsheetscrawler.services;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.topica.datashine.spreadsheetscrawler.constants.TICConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.TICService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTICService {

  @Autowired
  private TICService ticService;

  private static final String DRIVE_ID_TIC = "18HT3DE18kuq2baJu5QuR-hhhEpbVCpUj";

  @Test
  public void testImportDataFromFolderThuong() throws IOException {
    ticService.importData(DRIVE_ID_TIC, TICConstants.SHEETS_DATA);
  }

}
