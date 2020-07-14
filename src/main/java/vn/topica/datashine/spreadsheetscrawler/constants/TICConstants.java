package vn.topica.datashine.spreadsheetscrawler.constants;

import java.util.Arrays;
import java.util.List;

public interface TICConstants {

  int COLUMN_NUM_HO_TEN = 0;
  int COLUMN_NUM_SDT = 1;
  int COLUMN_NUM_EMAIL = 2;
  int COLUMN_NUM_TRANG_THAI_CS = 3;
  int COLUMN_NUM_TRANG_THAI_GOI = 4;
  int COLUMN_NUM_LEVEL = 5;
  int COLUMN_NUM_NGAY_DK = 6;
  int COLUMN_NUM_KENH = 7;
  int COLUMN_NUM_MAU_QC = 8;
  int COLUMN_NUM_TU_KHOA = 9;
  int COLUMN_NUM_GHI_CHU = 10;
  int COLUMN_NUM_NGAY_BG_TVTS = 11;
  int COLUMN_NUM_TVTS = 12;
  int COLUMN_NUM_NGHIEM_THU = 13;
  int COLUMN_NUM_SO_CALL = 14;
  int COLUMN_NUM_NOI_DUNG_CALL = 15;
  int COLUMN_NUM_NGAY_GOI_GAN_NHAT = 16;
  int COLUMN_NUM_NGAY_GOI_HEN_LAI = 17;
  int COLUMN_NUM_KHO = 18;
  int COLUMN_NUM_NGAY_BG_QTTT = 19;
  int COLUMN_NUM_NGAY_TIC = 20;
  int COLUMN_NUM_FLOW_NHAN_BG = 21;
  int COLUMN_NUM_TYPE = 22;
  int COLUMN_NUM_GC1 = 23;
  int COLUMN_NUM_GC2 = 24;
  int COLUMN_NUM_GC3 = 25;
  String COLUMN_RANGE_TIC = "!A2:Z";
  String FILE_FORMAT = "^\\d{4}\\d{2}\\d{2}_((D|M|W)(T|T1|T3)|(GS|GC))_(fl001|fl002|fl003|fl005|fl006|fl009|flna|flcs18|flda)$";

  List<String> SHEETS_DATA = Arrays.asList("data");
}
