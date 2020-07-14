package vn.topica.datashine.spreadsheetscrawler.utils.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import vn.topica.datashine.spreadsheetscrawler.constants.L1SXConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SX;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SXCs18;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SXD500;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SXDuan;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SXThuong;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SXVip;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

@Slf4j
public class L1SXUtil {

  private L1SXUtil() {
  }

  ;

  /**
   * @return all L1SX data from spreadsheet file
   */
  public static List<L1SX> getAllDataFromFile(Sheets sheetsService,
      String spreadsheetId,
      String sheetName, int typeOfData) throws QTTTException {

    List<L1SX> l1sxList = new ArrayList<>();

    String range = sheetName + L1SXConstants.COLUMN_RANGE_L1SX;
    ValueRange response;
    try {
      response = sheetsService.spreadsheets().values().get(spreadsheetId, range)
          .execute();
    } catch (Exception e) {
      throw new QTTTException("File: " + spreadsheetId + " can't find data in sheet name data");
    }

    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      log.info("L1SX: File " + sheetName + " No data found.");
    } else {
      for (List row : values) {
        if (ValidateDatatypeUtil.isBlankRow(row)) {
          continue;
        }

        L1SX l1SX = getValue(row, typeOfData);
        if (l1SX != null) {
          l1SX.setSpreadsheetsId(spreadsheetId);
          l1sxList.add(l1SX);
        }
      }
    }

    return l1sxList;
  }

  /**
   * Lay gia tri cua hang trong file
   */
  private static L1SX getValue(List row, int typeOfData) {
    int rowSize = row.size();
    L1SX l1SX = initializeRowDataObject(typeOfData);

    if (l1SX == null) {
      return null;
    }

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      switch (i) {
        case L1SXConstants.COLUMN_NUM_STT:
          break;
        case L1SXConstants.COLUMN_NUM_SAN_PHAM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setSanPham("");
          } else {
            l1SX.setSanPham(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_NAME:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setName("");
          } else {
            l1SX.setName(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_PHONE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setPhone("");
          } else {
            l1SX.setPhone(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_EMAIL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setEmail("");
          } else {
            l1SX.setEmail(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_KENH:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setKenh("");
          } else {
            l1SX.setKenh(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_LEVEL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setLevel("");
          } else {
            l1SX.setLevel(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_KHO:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setKho("");
          } else {
            l1SX.setKho(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_NGAY_SX:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setNgaySx(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            l1SX.setNgaySx(convertedDate);
          }
          break;
        case L1SXConstants.COLUMN_NUM_LUONG:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setLuong("");
          } else {
            l1SX.setLuong(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_GC1:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setGc1("");
          } else {
            l1SX.setGc1(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_GC2:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setGc2("");
          } else {
            l1SX.setGc2(cellValue);
          }
          break;
        case L1SXConstants.COLUMN_NUM_GC3:
          if (cellValue.equals("null") || cellValue.equals("")) {
            l1SX.setGc3("");
          } else {
            l1SX.setGc3(cellValue);
          }
          break;

        default:
          break;
      }
    }
    return l1SX;
  }

  private static L1SX initializeRowDataObject(int dataType) {
    switch (dataType) {
      case TypeConstants.THUONG:
        return new L1SXThuong();

      case TypeConstants.VIP:
        return new L1SXVip();

      case TypeConstants.CS18:
        return new L1SXCs18();

      case TypeConstants.D500:
        return new L1SXD500();

      case TypeConstants.DUAN:
        return new L1SXDuan();

      default:
        break;
    }

    return null;
  }
}
