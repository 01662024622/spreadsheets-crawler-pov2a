package vn.topica.datashine.spreadsheetscrawler.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.constants.L1BGConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.files.GoogleDriveFile;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.models.l1bg.L1BG;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.repository.L1BGRepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.L1BGService;
import vn.topica.datashine.spreadsheetscrawler.utils.data.GoogleDriveFileUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.L1BGUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;

@Service
@Slf4j
public class L1BGServiceImpl implements L1BGService {

  @Value("${l1bg.driveid.thuong}")
  private String l1bgDriveIdThuong;

  @Value("${l1bg.driveid.vip}")
  private String l1bgDriveIdVip;

  @Value("${l1bg.driveid.d500}")
  private String l1bgDriveIdD500;

  @Value("${l1bg.driveid.cs18}")
  private String l1bgDriveIdCS18;

  @Value("${l1bg.driveid.duan}")
  private String l1bgDriveIdDuan;

  private Sheets sheetsService;
  private FileImportedRepository fileImportedRepository;
  private L1BGRepository l1BGRepository;

  @Autowired
  public L1BGServiceImpl(L1BGRepository l1BGRepository,
      FileImportedRepository fileImportedRepository)
      throws GeneralSecurityException, IOException {
    this.l1BGRepository = l1BGRepository;
    this.fileImportedRepository = fileImportedRepository;
    this.sheetsService = SheetsServiceUtil.getSheetsService();
  }

  @Override
  public void importData(String driveId, List<String> sheetNames) throws IOException {
    String fileFormat = getFileFormat(driveId);

    if (fileFormat == null) {
      return;
    }

    List<GoogleDriveFile> l1bgFiles = GoogleDriveService
        .listAllSpreadsheetsFiles(driveId, fileFormat);

    for (GoogleDriveFile file : l1bgFiles) {
      FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
      String fileId = file.getFileId();
      String fileModifiedTime = file.getModifiedTime();

      FileImported lastImportedFile = fileImportedRepository.findByFileID(fileId);
      if (lastImportedFile == null) {//file chua import
        log.info("L1BG: Detected new file : " + file.getFileName());
        saveNewFile(newFile, sheetNames);
      } else {
        newFile.setId(lastImportedFile.getId());
        String lastImportModifiedTime = lastImportedFile.getModifiedTime();

        //file co thay doi
        if (!fileModifiedTime.equals(lastImportModifiedTime)) {
          log.info("L1BG: Data from file : " + file.getFileName() + " has been modified");
          deleteFileBySheetsId(driveId, fileId);
          log.info("L1BG: Deleted all old data of sheet: " + file.getFileName());
          saveNewFile(newFile, sheetNames);
        } else {
          log.info("L1BG: Data from file : " + file.getFileName() + " has been imported");
        }
      }
    }

  }

  private void importData(String spreadsheetId, String sheetName, int typeOfData)
      throws IOException, QTTTException {
    List<L1BG> l1bgList = L1BGUtil
        .getAllDataFromFile(sheetsService, spreadsheetId, sheetName, typeOfData);
    this.save(l1bgList);
  }

  @Override
  public void importData(String spreadsheetId, String sheetName) throws IOException {

  }

  private void deleteFileBySheetsId(String driveId, String fileId) {
    int dataType = getTypeConstants(driveId);
    switch (dataType) {
      case (TypeConstants.THUONG):
        l1BGRepository.deleteAllBySheetsIdThuong(fileId);
        break;
      case (TypeConstants.VIP):
        l1BGRepository.deleteAllBySheetsIdVip(fileId);
        break;
      case (TypeConstants.CS18):
        l1BGRepository.deleteAllBySheetsIdCS18(fileId);
        break;
      case (TypeConstants.D500):
        l1BGRepository.deleteAllBySheetsIdD500(fileId);
        break;
      case (TypeConstants.DUAN):
        l1BGRepository.deleteAllBySheetsIdDuan(fileId);
        break;
      default:
        break;
    }
  }

  private void saveNewFile(FileImported newFile, List<String> sheetNames) throws IOException {
    String fileId = newFile.getFileId();
    String driveId = newFile.getDriveId();
    log.info("L1BG: Importing file: " + newFile.getFileName());

    for (String sheetName : sheetNames) {
      try {
        int dataType = getTypeConstants(driveId);
        if (dataType < 0) {
          return;
        }

        importData(fileId, sheetName, dataType);
      } catch (IOException e) {
        log.error(
            "L1BG: Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
        return;
      } catch (QTTTException e) {
        log.info(e.getMessage());
        return;
      }
    }

    log.info("L1BG: Imported file: " + newFile.getFileName() + " status: ok");
    fileImportedRepository.save(newFile);
  }

  @Override
  public L1BG save(L1BG object) {
    return l1BGRepository.save(object);
  }

  @Override
  public void save(List<L1BG> objects) {
    l1BGRepository.saveAll(objects);
  }

  @Override
  public L1BG findById(Long aLong) {
    return l1BGRepository.getOne(aLong);
  }

  @Override
  public List<L1BG> findAll() {
    return l1BGRepository.findAll();
  }

  private String getFileFormat(String driveId) {
    if (driveId.equals(l1bgDriveIdThuong)) {
      return L1BGConstants.FILE_FORMAT_THUONG;
    }
    if (driveId.equals(l1bgDriveIdVip)) {
      return L1BGConstants.FILE_FORMAT_VIP;
    }
    if (driveId.equals(l1bgDriveIdCS18)) {
      return L1BGConstants.FILE_FORMAT_CS18;
    }
    if (driveId.equals(l1bgDriveIdD500)) {
      return L1BGConstants.FILE_FORMAT_D500;
    }
    if (driveId.equals(l1bgDriveIdDuan)) {
      return L1BGConstants.FILE_FORMAT_DA;
    }

    return null;
  }

  private int getTypeConstants(String driveId) {
    if (driveId.equals(l1bgDriveIdThuong)) {
      return TypeConstants.THUONG;
    }
    if (driveId.equals(l1bgDriveIdVip)) {
      return TypeConstants.VIP;
    }
    if (driveId.equals(l1bgDriveIdD500)) {
      return TypeConstants.D500;
    }
    if (driveId.equals(l1bgDriveIdCS18)) {
      return TypeConstants.CS18;
    }
    if (driveId.equals(l1bgDriveIdDuan)) {
      return TypeConstants.DUAN;
    }

    return -1;
  }
}
