package vn.topica.datashine.spreadsheetscrawler.utils;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import org.junit.Test;
import vn.topica.datashine.spreadsheetscrawler.constants.C3BConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3B;
import vn.topica.datashine.spreadsheetscrawler.utils.data.C3BUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.REUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

public class TestUtil {

  @Test
  public void testParseDouble() {
    //Float data = Float.parseFloat("8.000.000");
    Double data = ValidateDatatypeUtil.validateDouble("8.000.000");
//    Date date = ValidateDatatypeUtil.validateDateString("2019-07")
    System.out.println(data);
  }

  @Test
  public void testRowData() throws GeneralSecurityException, IOException {
    Sheets sheetsService = SheetsServiceUtil.getSheetsService();
    String sheetsId = "1L45X629-XaQgxB53efI0iR8zwYpyVXkFF40GqFptJjA";
    System.out.println(C3BConstants.SHEETS_DATA.get(0)+C3BConstants.COLUMN_RANGE_C3B);
    List<C3B> c3BList = null;
    try {
      c3BList = C3BUtil.getAllDataFromFile(sheetsService, sheetsId, C3BConstants.SHEETS_DATA.get(0), TypeConstants.CS18);
    } catch (QTTTException e) {
      e.printStackTrace();
    }

    c3BList.stream().forEach(System.out::println);

  }
}
