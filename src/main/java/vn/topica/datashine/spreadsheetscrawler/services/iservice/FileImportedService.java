package vn.topica.datashine.spreadsheetscrawler.services.iservice;

import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.services.base.BaseService;

public interface FileImportedService extends BaseService<FileImported, Long> {
  FileImported findByFileId(String fileId);
}
