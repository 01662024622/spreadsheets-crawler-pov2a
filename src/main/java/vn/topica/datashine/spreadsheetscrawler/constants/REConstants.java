package vn.topica.datashine.spreadsheetscrawler.constants;

import java.util.Arrays;
import java.util.List;

public interface REConstants {

  int COLUMN_NUM_NGAY_BG_L8 = 0;
  int COLUMN_NUM_EMAIL_BG = 1;
  int COLUMN_NUM_SAN_PHAM = 2;
  int COLUMN_NUM_NAME = 3;
  int COLUMN_NUM_SDT = 4;
  int COLUMN_NUM_EMAIL = 5;
  int COLUMN_NUM_THUC_THU = 6;
  int COLUMN_NUM_HT_THANHTOAN = 7;
  int COLUMN_NUM_GIA_TRI_TAI_KHOAN = 8;
  int COLUMN_NUM_THOI_GIAN_HOC = 9;
  int COLUMN_NUM_NGUON_CTS = 10;
  int COLUMN_NUM_LOAI_HOC_VIEN = 11;
  int COLUMN_NUM_NGAY_TIEN_VE = 12;
  int COLUMN_NUM_LOAI_TAI_KHOAN = 13;
  int COLUMN_NUM_KHUYEN_MAI = 14;
  int COLUMN_NUM_CONTACT_ID = 15;
  int COLUMN_NUM_MA_KHOA_HOC = 16;
  int COLUMN_NUM_KENH_CRM = 17;
  int COLUMN_NUM_TVTS_CRM = 18;
  int COLUMN_NUM_FLOW_THEO_TVTS = 19;
  int COLUMN_NUM_KENH_QUY_DOI = 20;
  int COLUMN_NUM_NGAY_DANG_KY = 21;
  int COLUMN_NUM_XAC_NHAN_TIEN_VE = 22;
  int COLUMN_NUM_GC1 = 23;
  int COLUMN_NUM_GC2 = 24;
  int COLUMN_NUM_GC3 = 25;
  String COLUMN_RANGE_RE = "!A2:Z";
  String FILE_FORMAT = "^\\d{4}\\d{2}\\d{2}$";

  List<String> SHEETS_DATA = Arrays.asList("data");
}
