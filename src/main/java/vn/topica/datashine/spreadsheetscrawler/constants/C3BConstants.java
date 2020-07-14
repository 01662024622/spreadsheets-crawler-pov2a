package vn.topica.datashine.spreadsheetscrawler.constants;

import java.util.Arrays;
import java.util.List;

public interface C3BConstants {

  int COLUMN_NUM_SAN_PHAM = 0;
  int COLUMN_NUM_SUBMIT_TIME = 1;
  int COLUMN_NUM_HO_TEN = 2;
  int COLUMN_NUM_PHONE = 3;
  int COLUMN_NUM_EMAIL = 4;
  int COLUMN_NUM_TUOI = 5;
  int COLUMN_NUM_CONTACT_CHANNEL = 6;
  int COLUMN_NUM_ADS_LINK = 7;
  int COLUMN_NUM_UTM_SOURCE = 8;
  int COLUMN_NUM_UTM_TEAM = 9;
  int COLUMN_NUM_UTM_TERM = 10;
  int COLUMN_NUM_UTM_MEDIUM = 11;
  int COLUMN_NUM_UTM_AGENT = 12;
  int COLUMN_NUM_UTM_CAMPAIGN = 13;
  int COLUMN_NUM_UTM_CONTENT = 14;
  int COLUMN_NUM_UTM_FLOW = 15;
  int COLUMN_NUM_GC1 = 16;
  int COLUMN_NUM_GC2 = 17;
  int COLUMN_NUM_GC3 = 18;

  String COLUMN_RANGE_C3B = "!A2:S";
  String FILE_FORMAT_THUONG = "^\\d{4}\\d{2}\\d{2}_C3B$";
  String FILE_FORMAT_VIP = "^\\d{4}\\d{2}\\d{2}_C3BVIP$";
  String FILE_FORMAT_D500 = "^\\d{4}\\d{2}\\d{2}_C3BD500$";
  String FILE_FORMAT_CS18 = "^\\d{4}\\d{2}\\d{2}_C3BCS18$";
  String FILE_FORMAT_DA = "^\\d{4}\\d{2}\\d{2}_C3BDA$";


  List<String> SHEETS_DATA = Arrays.asList("data");

}
