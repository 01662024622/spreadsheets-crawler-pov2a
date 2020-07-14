package vn.topica.datashine.spreadsheetscrawler.files;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleDriveFile {

  private String fileId;
  private String fileName;
  private String folderId;
  private String createdTime;
  private String modifiedTime;
}
