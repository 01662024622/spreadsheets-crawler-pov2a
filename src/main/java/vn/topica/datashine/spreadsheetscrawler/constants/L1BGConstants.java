package vn.topica.datashine.spreadsheetscrawler.constants;

import java.util.Arrays;
import java.util.List;

public interface L1BGConstants {
  int COLUMN_NUM_STT = 0;
  int COLUMN_NUM_SAN_PHAM = 1;
  int COLUMN_NUM_NAME = 2;
  int COLUMN_NUM_PHONE = 3;
  int COLUMN_NUM_EMAIL = 4;
  int COLUMN_NUM_KENH = 5;
  int COLUMN_NUM_LEVEL = 6;
  int COLUMN_NUM_KHO = 7;
  int COLUMN_NUM_NGAY_SX = 8;
  int COLUMN_NUM_NGAY_BG = 9;
  int COLUMN_NUM_LUONG = 10;
  int COLUMN_NUM_LUONG_NHAN_BG = 11;
  int COLUMN_NUM_GC1 = 12;
  int COLUMN_NUM_GC2 = 13;
  int COLUMN_NUM_GC3 = 14;

  String COLUMN_RANGE_L1BG = "!A2:O";
  String FILE_FORMAT_THUONG = "^\\d{4}\\d{2}\\d{2}_L1BG$";
  String FILE_FORMAT_VIP = "^\\d{4}\\d{2}\\d{2}_L1BGVIP$";
  String FILE_FORMAT_D500 = "^\\d{4}\\d{2}\\d{2}_L1BGD500$";
  String FILE_FORMAT_CS18 = "^\\d{4}\\d{2}\\d{2}_L1BGCS18$";
  String FILE_FORMAT_DA = "^\\d{4}\\d{2}\\d{2}_L1BGDA$";


  List<String> SHEETS_DATA = Arrays.asList("data");
}
