package vn.topica.datashine.spreadsheetscrawler.utils.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import vn.topica.datashine.spreadsheetscrawler.constants.C3BConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3B;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3BCs18;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3BD500;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3BDuan;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3BThuong;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3BVip;
import vn.topica.datashine.spreadsheetscrawler.utils.validate.ValidateDatatypeUtil;

@Slf4j
public class C3BUtil {

  private C3BUtil() {
  }

  ;

  /**
   * @return all C3B data from spreadsheet file
   */
  public static List<C3B> getAllDataFromFile(Sheets sheetsService,
      String spreadsheetId,
      String sheetName, int typeOfData) throws QTTTException {

    List<C3B> c3bList = new ArrayList<>();

    String range = sheetName + C3BConstants.COLUMN_RANGE_C3B;
    ValueRange response;
    try {
      response = sheetsService.spreadsheets().values().get(spreadsheetId, range)
          .execute();
    } catch (Exception e) {
      throw new QTTTException("File: " + spreadsheetId + " can't find data in sheet name data");
    }

    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      log.info("C3B: File " + sheetName + " No data found.");
    } else {
      for (List row : values) {
        if (ValidateDatatypeUtil.isBlankRow(row)) {
          continue;
        }

        C3B c3b = getValue(row, typeOfData);
        if (c3b != null) {
          c3b.setSpreadsheetsId(spreadsheetId);
          c3bList.add(c3b);
        }
      }
    }

    return c3bList;
  }

  /**
   * Lay gia tri cua hang trong file
   */
  private static C3B getValue(List row, int typeOfData) {
    int rowSize = row.size();
    C3B c3b = initializeRowDataObject(typeOfData);

    if (c3b == null) {
      return null;
    }

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      switch (i) {
        case C3BConstants.COLUMN_NUM_SAN_PHAM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setSanPham("");
          } else {
            c3b.setSanPham(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_SUBMIT_TIME:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setSubmitTime(null);
          } else {
            //c3b.setSubmitTime(cellValue);
            Date convertedDate = ValidateDatatypeUtil.validateDateString(cellValue);
            c3b.setSubmitTime(convertedDate);
          }
          break;
        case C3BConstants.COLUMN_NUM_HO_TEN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setHoTen("");
          } else {
            c3b.setHoTen(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_PHONE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setPhone("");
          } else {
            c3b.setPhone(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_EMAIL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setEmail("");
          } else {
            c3b.setEmail(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_TUOI:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setTuoi(null);
          } else {
            Long data = ValidateDatatypeUtil.validateIntegerString(cellValue);
            c3b.setTuoi(data);
          }
          break;
        case C3BConstants.COLUMN_NUM_CONTACT_CHANNEL:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setContactChannel("");
          } else {
            c3b.setContactChannel(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_ADS_LINK:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setAdsLink("");
          } else {
            c3b.setAdsLink(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_SOURCE:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmSource("");
          } else {
            c3b.setUtmSource(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_TEAM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmTeam("");
          } else {
            c3b.setUtmTeam(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_TERM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmTerm("");
          } else {
            c3b.setUtmTerm(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_MEDIUM:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmMedium("");
          } else {
            c3b.setUtmMedium(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_AGENT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmAgent("");
          } else {
            c3b.setUtmAgent(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_CAMPAIGN:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmCampaign("");
          } else {
            c3b.setUtmCampaign(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_CONTENT:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmContent("");
          } else {
            c3b.setUtmContent(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_UTM_FLOW:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setUtmFlow("");
          } else {
            c3b.setUtmFlow(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_GC1:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setGc1("");
          } else {
            c3b.setGc1(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_GC2:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setGc2("");
          } else {
            c3b.setGc2(cellValue);
          }
          break;
        case C3BConstants.COLUMN_NUM_GC3:
          if (cellValue.equals("null") || cellValue.equals("")) {
            c3b.setGc3("");
          } else {
            c3b.setGc3(cellValue);
          }
          break;

        default:
          break;
      }
    }
    return c3b;
  }

  private static C3B initializeRowDataObject(int dataType) {
    switch (dataType) {
      case TypeConstants.THUONG:
        return new C3BThuong();

      case TypeConstants.VIP:
        return new C3BVip();

      case TypeConstants.CS18:
        return new C3BCs18();

      case TypeConstants.D500:
        return new C3BD500();

      case TypeConstants.DUAN:
        return new C3BDuan();

      default:
        break;
    }

    return null;
  }
}
