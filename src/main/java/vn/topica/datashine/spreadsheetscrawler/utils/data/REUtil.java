package vn.topica.datashine.spreadsheetscrawler.utils.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import vn.topica.datashine.spreadsheetscrawler.constants.REConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.re.RE;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

@Slf4j
public class REUtil {

  private REUtil() {
  }

  ;

  /**
   * @return all RE data from spreadsheet file
   */
  public static List<RE> getAllDataFromFile(Sheets sheetsService, String spreadsheetId,
      String sheetName) throws QTTTException {

    List<RE> reList = new ArrayList<>();

    String range = sheetName + REConstants.COLUMN_RANGE_RE;
    ValueRange response;
    try {
      response = sheetsService.spreadsheets().values().get(spreadsheetId, range)
          .execute();
    } catch (Exception e) {
      throw new QTTTException("File: " + spreadsheetId + " can't find data in sheet name data");
    }
    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      log.info("RE: File " + sheetName + " No data found.");
    } else {
      for (List row : values) {
        if (ValidateDatatypeUtil.isBlankRow(row)) {
          continue;
        }

        RE re = getValue(row);
        if (re != null) {
          re.setSpreadsheetsId(spreadsheetId);
          reList.add(re);
        }
      }
    }

    return reList;
  }

  /**
   * Lay gia tri cua hang trong file
   */
  private static RE getValue(List row) {
    int rowSize = row.size();
    RE re = new RE();

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      switch (i) {
        case REConstants.COLUMN_NUM_NGAY_BG_L8:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setNgayBgl8(null);
          } else {
            Date dateString = ValidateDatatypeUtil.validateDateString(cellValue);
            re.setNgayBgl8(dateString);
          }
          break;
        case REConstants.COLUMN_NUM_EMAIL_BG:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setEmailBg(null);
          } else {
            re.setEmailBg(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_SAN_PHAM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setSanPham(null);
          } else {
            re.setSanPham(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_NAME:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setName(null);
          } else {
            re.setName(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_SDT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setSdt("");
          } else {
            re.setSdt(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_EMAIL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setEmail("");
          } else {
            re.setEmail(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_THUC_THU:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setThucThu(null);
          } else {
            Double data = ValidateDatatypeUtil.validateDouble(cellValue);
            re.setThucThu(data);
          }
          break;
        case REConstants.COLUMN_NUM_HT_THANHTOAN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setHtThanhtoan("");
          } else {
            re.setHtThanhtoan(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_GIA_TRI_TAI_KHOAN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setGiatritaikhoan(null);
          } else {
            Double data = ValidateDatatypeUtil.validateDouble(cellValue);
            re.setGiatritaikhoan(data);
          }
          break;
        case REConstants.COLUMN_NUM_THOI_GIAN_HOC:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setThoigianhoc(null);
          } else {
            Long data = ValidateDatatypeUtil.validateIntegerString(cellValue);
            re.setThoigianhoc(data);
          }
          break;
        case REConstants.COLUMN_NUM_NGUON_CTS:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setNguoncts("");
          } else {
            re.setNguoncts(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_LOAI_HOC_VIEN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setLoaihocvien("");
          } else {
            re.setLoaihocvien(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_NGAY_TIEN_VE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setNgaytienve(null);
          } else {
            Date dateString = ValidateDatatypeUtil.validateDateString(cellValue);
            re.setNgaytienve(dateString);
          }
          break;
        case REConstants.COLUMN_NUM_LOAI_TAI_KHOAN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setLoaitaikhoan("");
          } else {
            re.setLoaitaikhoan(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_KHUYEN_MAI:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setKhuyenmai("");
          } else {
            re.setKhuyenmai(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_CONTACT_ID:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setContactid("");
          } else {
            re.setContactid(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_MA_KHOA_HOC:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setMakhoahoc("");
          } else {
            re.setMakhoahoc(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_KENH_CRM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setKenhcrm(null);
          } else {
            re.setKenhcrm(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_TVTS_CRM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setTvtscrm("");
          } else {
            re.setTvtscrm(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_FLOW_THEO_TVTS:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setFlowtheotvts("");
          } else {
            re.setFlowtheotvts(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_KENH_QUY_DOI:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setKenhquidoi(null);
          } else {
            re.setKenhquidoi(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_NGAY_DANG_KY:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setNgaydangky(null);
          } else {
            Date dateString = ValidateDatatypeUtil.validateDateString(cellValue);
            re.setNgaydangky(dateString);
          }
          break;
        case REConstants.COLUMN_NUM_XAC_NHAN_TIEN_VE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setXacnhantienve("");
          } else {
            re.setXacnhantienve(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_GC1:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setGc1("");
          } else {
            re.setGc1(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_GC2:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setGc2("");
          } else {
            re.setGc2(cellValue);
          }
          break;
        case REConstants.COLUMN_NUM_GC3:
          if (cellValue.equals("null") || cellValue.equals("")) {
            re.setGc3("");
          } else {
            re.setGc3(cellValue);
          }
          break;
        default:
          break;
      }
    }
    return re;
  }
}
