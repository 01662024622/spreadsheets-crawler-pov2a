package vn.topica.datashine.spreadsheetscrawler.utils.validate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ValidateDatatypeUtil {

  private ValidateDatatypeUtil() {
  }

  ;

  public static Date validateDateString(String dateString) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dfFull = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    try {
      Date result = dfFull.parse(dateString);
      return result;
    } catch (ParseException e) {
      try {
        Date result = df.parse(dateString);
        return result;
      } catch (ParseException ex) {
        return null;
      }
    }
  }

  public static Long validateIntegerString(String integerString) {
    try {
      Long result = Long.parseLong(integerString);
      return result;
    } catch (NumberFormatException e) {
      return null;
    }
  }


  public static Double validateDouble(String doubleString) {
    try {
      NumberFormat format = NumberFormat.getInstance(Locale.ITALY);
      Number number = format.parse(doubleString);
      Double result = number.doubleValue();
      return result;
    } catch (NumberFormatException | ParseException e) {
      return null;
    }
  }

  public static boolean isBlankRow(List<Object> row) {
    int blankCell = 0;
    int rowSize = row.size();

    for (int i = 0; i < rowSize; i++) {
      String cellValue = row.get(i).toString();
      if (cellValue.equals("null") || cellValue.equals("")) {
        blankCell++;
      }
    }

    return blankCell == rowSize;
  }
}
