package vn.topica.datashine.spreadsheetscrawler.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.constants.C3BConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.files.GoogleDriveFile;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3B;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.repository.C3BRepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.C3BService;
import vn.topica.datashine.spreadsheetscrawler.utils.data.C3BUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.GoogleDriveFileUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;

@Service
@Slf4j
public class C3BServiceImpl implements C3BService {

  @Value("${c3b.driveid.thuong}")
  private String c3bDriveIdThuong;

  @Value("${c3b.driveid.vip}")
  private String c3bDriveIdVip;

  @Value("${c3b.driveid.d500}")
  private String c3bDriveIdD500;

  @Value("${c3b.driveid.cs18}")
  private String c3bDriveIdCS18;

  @Value("${c3b.driveid.duan}")
  private String c3bDriveIdDuan;

  private Sheets sheetsService;
  private FileImportedRepository fileImportedRepository;
  private C3BRepository c3BRepository;

  @Autowired
  public C3BServiceImpl(C3BRepository c3BRepository,
      FileImportedRepository fileImportedRepository)
      throws GeneralSecurityException, IOException {
    this.c3BRepository = c3BRepository;
    this.fileImportedRepository = fileImportedRepository;
    this.sheetsService = SheetsServiceUtil.getSheetsService();
  }

  @Override
  public void importData(String driveId, List<String> sheetNames) throws IOException {
    String fileFormat = getFileFormat(driveId);

    if (fileFormat == null) {
      return;
    }

    List<GoogleDriveFile> c3bFiles = GoogleDriveService
        .listAllSpreadsheetsFiles(driveId, fileFormat);

    for (GoogleDriveFile file : c3bFiles) {
      FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
      String fileId = file.getFileId();
      String fileModifiedTime = file.getModifiedTime();

      FileImported lastImportedFile = fileImportedRepository.findByFileID(fileId);
      if (lastImportedFile == null) {//file chua import
        log.info("C3B: Detected new file : " + file.getFileName());
        saveNewFile(newFile, sheetNames);
      } else {
        newFile.setId(lastImportedFile.getId());
        String lastImportModifiedTime = lastImportedFile.getModifiedTime();

        //file co thay doi
        if (!fileModifiedTime.equals(lastImportModifiedTime)) {
          log.info("C3B: Data from file : " + file.getFileName() + " has been modified");
          deleteFileBySheetsId(driveId, fileId);
          log.info("C3B: Deleted all old data of sheet: " + file.getFileName());
          saveNewFile(newFile, sheetNames);
        } else {
          log.info("C3B: Data from file : " + file.getFileName() + " has been imported");
        }
      }
    }
  }


  public void importData(String spreadsheetId, String sheetName, int typeOfData)
      throws IOException, QTTTException {
    List<C3B> c3BList = C3BUtil
          .getAllDataFromFile(sheetsService, spreadsheetId, sheetName, typeOfData);

    this.save(c3BList);
  }

  @Override
  public void importData(String spreadsheetId, String sheetName) throws IOException {

  }

  private void deleteFileBySheetsId(String driveId, String fileId) {
    int dataType = getTypeConstants(driveId);
    switch (dataType) {
      case (TypeConstants.THUONG):
        c3BRepository.deleteAllBySheetsIdThuong(fileId);
        break;
      case (TypeConstants.VIP):
        c3BRepository.deleteAllBySheetsIdVip(fileId);
        break;
      case (TypeConstants.CS18):
        c3BRepository.deleteAllBySheetsIdCS18(fileId);
        break;
      case (TypeConstants.D500):
        c3BRepository.deleteAllBySheetsIdD500(fileId);
        break;
      case (TypeConstants.DUAN):
        c3BRepository.deleteAllBySheetsIdDuan(fileId);
        break;
      default:
        break;
    }
  }

  private void saveNewFile(FileImported newFile, List<String> sheetNames) {
    String fileId = newFile.getFileId();
    String driveId = newFile.getDriveId();
    log.info("C3B: Importing file: " + newFile.getFileName());

    for (String sheetName : sheetNames) {
      try {
        int dataType = getTypeConstants(driveId);
        if (dataType < 0) {
          return;
        }

        importData(fileId, sheetName, dataType);
      } catch (IOException e) {
        log.error("C3B: Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
        return;
      } catch (QTTTException e) {
        log.info(e.getMessage());
        return;
      }
    }

    log.info("C3B: Imported file: " + newFile.getFileName() + " status: ok");
    fileImportedRepository.save(newFile);
  }

  @Override
  public C3B save(C3B object) {
    return c3BRepository.save(object);
  }

  @Override
  public void save(List<C3B> objects) {
    c3BRepository.saveAll(objects);
  }

  @Override
  public C3B findById(Long aLong) {
    return c3BRepository.getOne(aLong);
  }

  @Override
  public List<C3B> findAll() {
    return c3BRepository.findAll();
  }

  private String getFileFormat(String driveId) {
    if (driveId.equals(c3bDriveIdThuong)) {
      return C3BConstants.FILE_FORMAT_THUONG;
    }
    if (driveId.equals(c3bDriveIdVip)) {
      return C3BConstants.FILE_FORMAT_VIP;
    }
    if (driveId.equals(c3bDriveIdCS18)) {
      return C3BConstants.FILE_FORMAT_CS18;
    }
    if (driveId.equals(c3bDriveIdD500)) {
      return C3BConstants.FILE_FORMAT_D500;
    }
    if (driveId.equals(c3bDriveIdDuan)) {
      return C3BConstants.FILE_FORMAT_DA;
    }

    return null;
  }

  private int getTypeConstants(String driveId) {
    if (driveId.equals(c3bDriveIdThuong)) {
      return TypeConstants.THUONG;
    }
    if (driveId.equals(c3bDriveIdVip)) {
      return TypeConstants.VIP;
    }
    if (driveId.equals(c3bDriveIdD500)) {
      return TypeConstants.D500;
    }
    if (driveId.equals(c3bDriveIdCS18)) {
      return TypeConstants.CS18;
    }
    if (driveId.equals(c3bDriveIdDuan)) {
      return TypeConstants.DUAN;
    }

    return -1;
  }
}
