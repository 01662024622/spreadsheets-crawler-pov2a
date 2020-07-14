package vn.topica.datashine.spreadsheetscrawler.utils.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import vn.topica.datashine.spreadsheetscrawler.constants.TonKhoConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.tonkho.TonKho;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

@Slf4j
public class TonKhoUtil {

  private TonKhoUtil() {
  }

  ;

  /**
   * @return all TonKho data from spreadsheet file
   */
  public static List<TonKho> getAllDataFromFile(Sheets sheetsService,
      String spreadsheetId, String sheetName) throws QTTTException {

    List<TonKho> tonKhoList = new ArrayList<>();

    String range = sheetName + TonKhoConstants.COLUMN_RANGE_TONKHO;
    ValueRange response;
    try {
      response = sheetsService.spreadsheets().values().get(spreadsheetId, range)
          .execute();
    } catch (Exception e) {
      throw new QTTTException("File: " + spreadsheetId + " can't find data in sheet name data");
    }
    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      log.info("Tonkho: File " + sheetName + " No data found.");
    } else {
      for (List row : values) {
        if (ValidateDatatypeUtil.isBlankRow(row)) {
          continue;
        }

        TonKho tonKho = getValue(row);
        if (tonKho != null) {
          tonKho.setSpreadsheetsId(spreadsheetId);
          tonKhoList.add(tonKho);
        }
      }
    }

    return tonKhoList;
  }

  /**
   * Lay gia tri cua hang trong file
   */
  private static TonKho getValue(List row) {
    int rowSize = row.size();
    TonKho tonKho = new TonKho();

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      switch (i) {
        case TonKhoConstants.COLUMN_NUM_STT:
          break;
        case TonKhoConstants.COLUMN_NUM_TKID:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setTkId("");
          } else {
            tonKho.setTkId(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_NAME:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setName("");
          } else {
            tonKho.setName(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_SDT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setSdt("");
          } else {
            tonKho.setSdt(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_EMAIL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setEmail("");
          } else {
            tonKho.setEmail(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_KENH:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setKenh("");
          } else {
            tonKho.setKenh(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_LEVEL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setLevel("");
          } else {
            tonKho.setLevel(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_KHO:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setKho("");
          } else {
            tonKho.setKho(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_NGAY_DK:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setNgayDk(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tonKho.setNgayDk(convertedDate);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_NGAY_CUTOFF:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setNgayCutoff(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tonKho.setNgayCutoff(convertedDate);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_GC1:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setGc1("");
          } else {
            tonKho.setGc1(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_GC2:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setGc2("");
          } else {
            tonKho.setGc2(cellValue);
          }
          break;
        case TonKhoConstants.COLUMN_NUM_GC3:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tonKho.setGc3("");
          } else {
            tonKho.setGc3(cellValue);
          }
          break;

        default:
          break;
      }
    }
    return tonKho;
  }
}
