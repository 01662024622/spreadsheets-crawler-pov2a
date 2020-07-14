package vn.topica.datashine.spreadsheetscrawler.utils.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import vn.topica.datashine.spreadsheetscrawler.constants.TICConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.tic.TIC;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

@Slf4j
public class TICUtil {

  private TICUtil() {
  }

  ;

  /**
   * @return all TIC data from spreadsheet file
   */
  public static List<TIC> getAllDataFromFile(Sheets sheetsService, String spreadsheetId,
      String sheetName) throws QTTTException {

    List<TIC> ticList = new ArrayList<>();

    String range = sheetName + TICConstants.COLUMN_RANGE_TIC;
    ValueRange response;
    try {
      response = sheetsService.spreadsheets().values().get(spreadsheetId, range)
          .execute();
    } catch (Exception e) {
      throw new QTTTException("File: " + spreadsheetId + " can't find data in sheet name data");
    }

    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      log.info("TIC: File " + sheetName + " No data found.");
    } else {
      for (List row : values) {
        if (ValidateDatatypeUtil.isBlankRow(row)) {
          continue;
        }

        TIC tic = getValue(row);
        if (tic != null) {
          tic.setSpreadsheetsId(spreadsheetId);
          ticList.add(tic);
        }
      }
    }

    return ticList;
  }

  /**
   * Lay gia tri cua hang trong file
   */
  private static TIC getValue(List row) {
    int rowSize = row.size();
    TIC tic = new TIC();

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      switch (i) {
        case TICConstants.COLUMN_NUM_HO_TEN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setHoTen("");
          } else {
            tic.setHoTen(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_SDT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setSdt("");
          } else {
            tic.setSdt(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_EMAIL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setEmail("");
          } else {
            tic.setEmail(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_TRANG_THAI_CS:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setTrangThaiCs("");
          } else {
            tic.setTrangThaiCs(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_TRANG_THAI_GOI:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setTrangThaiGoi("");
          } else {
            tic.setTrangThaiGoi(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_LEVEL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setLevel("");
          } else {
            tic.setLevel(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_NGAY_DK:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNgayDk(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tic.setNgayDk(convertedDate);
          }
          break;
        case TICConstants.COLUMN_NUM_KENH:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setKenh("");
          } else {
            tic.setKenh(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_MAU_QC:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setMauQc("");
          } else {
            tic.setMauQc(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_TU_KHOA:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setTuKhoa("");
          } else {
            tic.setTuKhoa(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_GHI_CHU:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setGhiChu("");
          } else {
            tic.setGhiChu(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_NGAY_BG_TVTS:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNgayBgTvts(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tic.setNgayBgTvts(convertedDate);
          }
          break;
        case TICConstants.COLUMN_NUM_TVTS:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setTvts("");
          } else {
            tic.setTvts(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_NGHIEM_THU:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNghiemThu("");
          } else {
            tic.setNghiemThu(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_SO_CALL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setSoCall(null);
          } else {
            Long data = ValidateDatatypeUtil.validateIntegerString(cellValue);
            tic.setSoCall(data);
          }
          break;
        case TICConstants.COLUMN_NUM_NOI_DUNG_CALL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNoiDungCall("");
          } else {
            tic.setNoiDungCall(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_NGAY_GOI_GAN_NHAT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNgayGoiGanNhat(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tic.setNgayGoiGanNhat(convertedDate);
          }
          break;
        case TICConstants.COLUMN_NUM_NGAY_GOI_HEN_LAI:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNgayHenGoiLai(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tic.setNgayHenGoiLai(convertedDate);
          }
          break;
        case TICConstants.COLUMN_NUM_KHO:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setKho("");
          } else {
            tic.setKho(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_NGAY_BG_QTTT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNgayBgQttt(null);
          } else {
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            tic.setNgayBgQttt(convertedDate);
          }
          break;
        case TICConstants.COLUMN_NUM_NGAY_TIC:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setNgayTic("");
          } else {
            tic.setNgayTic(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_FLOW_NHAN_BG:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setFlowNhanBg("");
          } else {
            tic.setFlowNhanBg(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_TYPE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setType("");
          } else {
            tic.setType(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_GC1:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setGc1("");
          } else {
            tic.setGc1(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_GC2:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setGc2("");
          } else {
            tic.setGc2(cellValue);
          }
          break;
        case TICConstants.COLUMN_NUM_GC3:
          if (cellValue.equals("null") || cellValue.equals("")) {
            tic.setGc3("");
          } else {
            tic.setGc3(cellValue);
          }
          break;
        default:
          break;
      }
    }
    return tic;
  }
}
