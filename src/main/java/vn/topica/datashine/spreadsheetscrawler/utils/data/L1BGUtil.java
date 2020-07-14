package vn.topica.datashine.spreadsheetscrawler.utils.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import vn.topica.datashine.spreadsheetscrawler.constants.L1BGConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BG;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BGCs18;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BGD500;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BGDuan;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BGThuong;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BGVip;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

@Slf4j
public class L1BGUtil {

  private L1BGUtil() {
  }

  ;

  /**
   * @return all L1BG data from spreadsheet file
   */
  public static List<L1BG> getAllDataFromFile(Sheets sheetsService,
      String spreadsheetId,
      String sheetName, int typeOfData) throws QTTTException {

    List<L1BG> l1BGList = new ArrayList<>();

    String range = sheetName + L1BGConstants.COLUMN_RANGE_L1BG;
    ValueRange response;
    try {
      response = sheetsService.spreadsheets().values().get(spreadsheetId, range)
          .execute();
    } catch (Exception e) {
      throw new QTTTException("File: " + spreadsheetId + " can't find data in sheet name data");
    }

    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      log.info("L1BG: File " + sheetName + " No data found.");
    } else {
      for (List row : values) {
        if (ValidateDatatypeUtil.isBlankRow(row)) {
          continue;
        }

        L1BG l1bg = getValue(row, typeOfData);
        if (l1bg != null) {
          l1bg.setSpreadsheetsId(spreadsheetId);
          l1BGList.add(l1bg);
        }
      }
    }

    return l1BGList;
  }

  /**
   * Lay gia tri cua hang trong file
   */
  private static L1BG getValue(List row, int typeOfData) {

    int rowSize = row.size();
    L1BG l1bg = initializeRowDataObject(typeOfData);

    if (l1bg == null) {
      return null;
    }

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      switch (i) {
        case L1BGConstants.COLUMN_NUM_STT:
          break;
        case L1BGConstants.COLUMN_NUM_SAN_PHAM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setSanPham("");
          } else {
            l1bg.setSanPham(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_NAME:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setName("");
          } else {
            l1bg.setName(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_PHONE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setPhone("");
          } else {
            l1bg.setPhone(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_EMAIL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setEmail("");
          } else {
            l1bg.setEmail(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_KENH:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setKenh("");
          } else {
            l1bg.setKenh(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_LEVEL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setLevel("");
          } else {
            l1bg.setLevel(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_KHO:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setKho("");
          } else {
            l1bg.setKho(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_NGAY_SX:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setNgaySx(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            l1bg.setNgaySx(convertedDate);
          }
          break;
        case L1BGConstants.COLUMN_NUM_NGAY_BG:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setNgayBg(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            l1bg.setNgayBg(convertedDate);
          }
          break;
        case L1BGConstants.COLUMN_NUM_LUONG:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setLuong("");
          } else {
            l1bg.setLuong(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_LUONG_NHAN_BG:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setLuongNhanBg("");
          } else {
            l1bg.setLuongNhanBg(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_GC1:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setGc1("");
          } else {
            l1bg.setGc1(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_GC2:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setGc2("");
          } else {
            l1bg.setGc2(cellValue);
          }
          break;
        case L1BGConstants.COLUMN_NUM_GC3:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1bg.setGc3("");
          } else {
            l1bg.setGc3(cellValue);
          }
          break;

        default:
          break;
      }
    }
    return l1bg;
  }

  private static L1BG initializeRowDataObject(int dataType) {
    switch (dataType) {
      case TypeConstants.THUONG:
        return new L1BGThuong();

      case TypeConstants.VIP:
        return new L1BGVip();

      case TypeConstants.CS18:
        return new L1BGCs18();

      case TypeConstants.D500:
        return new L1BGD500();

      case TypeConstants.DUAN:
        return new L1BGDuan();

      default:
        break;
    }

    return null;
  }
}
