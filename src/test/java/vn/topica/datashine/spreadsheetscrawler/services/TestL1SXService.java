package vn.topica.datashine.spreadsheetscrawler.services;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.topica.datashine.spreadsheetscrawler.constants.L1SXConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.L1SXService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestL1SXService {

  @Autowired
  private L1SXService l1SXService;

  private static final String DRIVE_ID_L1SX_THUONG = "1TWfHJsOaQ9L3-8Mse_yRNMgha7_YSV7x";
  private static final String DRIVE_ID_L1SX_VIP = "1IqY9pClmvOATRoyczI0RAMurSRqERXQs";
  private static final String DRIVE_ID_L1SX_D500 = "1VKRPsciEGW3l1T1nfRBS6_rQULgbkZ6l";
  private static final String DRIVE_ID_L1SX_CS18 = "1Z1q8dZxa_LHNUyrPCQ-WE-yBjTi9kxoY";
  private static final String DRIVE_ID_L1SX_DUAN = "1bIlPeP5pxMzEZCey7b9Pl-VpkpSuWbCf";

  @Test
  public void testImportDataFromFolderThuong() throws IOException {
    l1SXService.importData(DRIVE_ID_L1SX_THUONG, L1SXConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderVip() throws IOException {
    l1SXService.importData(DRIVE_ID_L1SX_VIP, L1SXConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderD500() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    l1SXService.importData(DRIVE_ID_L1SX_D500, L1SXConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderCs18() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    l1SXService.importData(DRIVE_ID_L1SX_CS18, L1SXConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderDA() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    l1SXService.importData(DRIVE_ID_L1SX_DUAN, L1SXConstants.SHEETS_DATA);
  }
}
