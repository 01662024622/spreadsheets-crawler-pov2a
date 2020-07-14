package vn.topica.datashine.spreadsheetscrawler.services;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.topica.datashine.spreadsheetscrawler.constants.C3BConstants;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.C3BService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestC3BService {

  @Autowired
  private C3BService c3BService;

  private static final String DRIVE_ID_C3B_THUONG = "1pUsbj2-VEHTScMoYQHqIU47dqCOGce3U";
  private static final String DRIVE_ID_C3B_VIP = "1audCbDJbjcczsHsx8fuYQAm_RAX1g0a4";
  private static final String DRIVE_ID_C3B_CS18 = "15OSyFt1yKUGtogk7XJApHwCzxbJiiEdf";
  private static final String DRIVE_ID_C3B_D500 = "13uFdvDqrcBRXRVHmf68FiLi85RUxlL7h";
  private static final String DRIVE_ID_C3B_DUAN = "1F9SneXPojo9SbEoF2Q-VapeNzn9cpz12";


  @Test
  public void testImportDataFromFolderThuong() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    c3BService.importData(DRIVE_ID_C3B_THUONG, C3BConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderVip() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    c3BService.importData(DRIVE_ID_C3B_VIP, C3BConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderD500() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    c3BService.importData(DRIVE_ID_C3B_D500, C3BConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderCs18() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    c3BService.importData(DRIVE_ID_C3B_CS18, C3BConstants.SHEETS_DATA);
  }

  @Test
  public void testImportDataFromFolderDA() throws IOException {
    //List<String> sheets = Arrays.asList("Thứ 2","Thứ 3");
    c3BService.importData(DRIVE_ID_C3B_DUAN, C3BConstants.SHEETS_DATA);
  }
}
