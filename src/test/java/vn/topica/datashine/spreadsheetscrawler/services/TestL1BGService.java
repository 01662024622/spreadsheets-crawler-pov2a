package vn.topica.datashine.spreadsheetscrawler.services;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.topica.datashine.spreadsheetscrawler.constants.L1BGConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.L1BGService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestL1BGService {

  @Autowired
  private L1BGService l1BGService;

  private static final String DRIVE_ID_L1BG_THUONG = "1RLSMw5EmcmVFXYBl6IcOhgIy7YTAV5t3";
  private static final String DRIVE_ID_L1BG_VIP = "16o5cRMSQzOaLG-MndGME2cBSfIOJTLrw";
  private static final String DRIVE_ID_L1BG_D500 = "1euG5Mz2PcHHn71zKSRbE93Q8d4C0stqS";
  private static final String DRIVE_ID_L1BG_CS18 = "1uLO2hB1sgf5E34BlyCDgbKIXnh-ZlQmp";
  private static final String DRIVE_ID_L1BG_DUAN = "1ObD8j702FJ-Nku2fEvaKwkQEd_aWMuGf";

  @Test
  public void testImportDataFromFolderThuong() throws IOException {
    l1BGService.importData(DRIVE_ID_L1BG_THUONG, L1BGConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderVip() throws IOException {
    l1BGService.importData(DRIVE_ID_L1BG_VIP, L1BGConstants.SHEETS_DATA);
  }


  @Test
  public void testImportDataFromFolderD500() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    l1BGService.importData(DRIVE_ID_L1BG_D500, L1BGConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderCs18() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    l1BGService.importData(DRIVE_ID_L1BG_CS18, L1BGConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderDA() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    l1BGService.importData(DRIVE_ID_L1BG_DUAN, L1BGConstants.SHEETS_DATA);
  }
}
