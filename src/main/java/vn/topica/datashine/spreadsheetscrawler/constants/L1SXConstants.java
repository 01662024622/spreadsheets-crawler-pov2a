package vn.topica.datashine.spreadsheetscrawler.constants;

import java.util.Arrays;
import java.util.List;

public interface L1SXConstants {

  int COLUMN_NUM_STT = 0;
  int COLUMN_NUM_SAN_PHAM = 1;
  int COLUMN_NUM_NAME = 2;
  int COLUMN_NUM_PHONE = 3;
  int COLUMN_NUM_EMAIL = 4;
  int COLUMN_NUM_KENH = 5;
  int COLUMN_NUM_LEVEL = 6;
  int COLUMN_NUM_KHO = 7;
  int COLUMN_NUM_NGAY_SX = 8;
  int COLUMN_NUM_LUONG = 9;
  int COLUMN_NUM_GC1 = 10;
  int COLUMN_NUM_GC2 = 11;
  int COLUMN_NUM_GC3 = 12;

  String COLUMN_RANGE_L1SX = "!A2:M";
  String FILE_FORMAT_THUONG = "^\\d{4}\\d{2}\\d{2}_L1SX$";
  String FILE_FORMAT_VIP = "^\\d{4}\\d{2}\\d{2}_L1SXVIP$";
  String FILE_FORMAT_D500 = "^\\d{4}\\d{2}\\d{2}_L1SXD500$";
  String FILE_FORMAT_CS18 = "^\\d{4}\\d{2}\\d{2}_L1SXCS18$";
  String FILE_FORMAT_DA = "^\\d{4}\\d{2}\\d{2}_L1SXDA$";


  List<String> SHEETS_DATA = Arrays.asList("data");
}
