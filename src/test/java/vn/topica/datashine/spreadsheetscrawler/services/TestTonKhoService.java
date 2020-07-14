package vn.topica.datashine.spreadsheetscrawler.services;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.topica.datashine.spreadsheetscrawler.constants.TonKhoConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.TonKhoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTonKhoService {

  @Autowired
  private TonKhoService tonKhoService;

  private final String DRIVE_ID_TonKho = "1_jS56_3SXBY4QREnUPQSUhAYJwcjeL9k";

  @Test
  public void testImportDataFromFolderThuong() throws IOException {
    tonKhoService.importData(DRIVE_ID_TonKho, TonKhoConstants.SHEETS_DATA);
  }

}
