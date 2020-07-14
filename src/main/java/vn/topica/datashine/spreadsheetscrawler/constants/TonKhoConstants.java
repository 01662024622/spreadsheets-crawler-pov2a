package vn.topica.datashine.spreadsheetscrawler.constants;

import java.util.Arrays;
import java.util.List;

public interface TonKhoConstants {

  int COLUMN_NUM_STT = 0;
  int COLUMN_NUM_TKID = 1;
  int COLUMN_NUM_NAME = 2;
  int COLUMN_NUM_SDT = 3;
  int COLUMN_NUM_EMAIL = 4;
  int COLUMN_NUM_KENH = 5;
  int COLUMN_NUM_LEVEL = 6;
  int COLUMN_NUM_KHO = 7;
  int COLUMN_NUM_NGAY_DK = 8;
  int COLUMN_NUM_NGAY_CUTOFF = 9;
  int COLUMN_NUM_GC1 = 10;
  int COLUMN_NUM_GC2 = 11;
  int COLUMN_NUM_GC3 = 12;

  String COLUMN_RANGE_TONKHO = "!A2:M";
  String FILE_FORMAT = "^\\d{4}\\d{2}\\d{2}";

  List<String> SHEETS_DATA = Arrays.asList("data");
}
