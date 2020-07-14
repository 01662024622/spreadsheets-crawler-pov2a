package vn.topica.datashine.spreadsheetscrawler.services.base;

import java.io.IOException;
import java.util.List;

public interface BaseSpreadsheetsService<T, ID> extends BaseService<T, ID> {

  void importData(String driveId, List<String> sheetNames) throws IOException;

  void importData(String spreadsheetId, String sheetName) throws IOException;

}
