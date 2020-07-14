package vn.topica.datashine.spreadsheetscrawler.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.constants.L1SXConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TypeConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.files.GoogleDriveFile;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SX;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.repository.L1SXRepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.L1SXService;
import vn.topica.datashine.spreadsheetscrawler.utils.data.GoogleDriveFileUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.L1SXUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;

@Service
@Slf4j
public class L1SXServiceImpl implements L1SXService {

  @Value("${l1sx.driveid.thuong}")
  private String l1sxDriveIdThuong;

  @Value("${l1sx.driveid.vip}")
  private String l1sxDriveIdVip;

  @Value("${l1sx.driveid.d500}")
  private String l1sxDriveIdD500;

  @Value("${l1sx.driveid.cs18}")
  private String l1sxDriveIdCS18;

  @Value("${l1sx.driveid.duan}")
  private String l1sxDriveIdDuan;

  private Sheets sheetsService;
  private FileImportedRepository fileImportedRepository;
  private L1SXRepository l1SXRepository;

  @Autowired
  public L1SXServiceImpl(L1SXRepository l1SXRepository,
      FileImportedRepository fileImportedRepository)
      throws GeneralSecurityException, IOException {
    this.l1SXRepository = l1SXRepository;
    this.fileImportedRepository = fileImportedRepository;
    this.sheetsService = SheetsServiceUtil.getSheetsService();
  }

  @Override
  public void importData(String driveId, List<String> sheetNames) throws IOException {
    String fileFormat = getFileFormat(driveId);

    if (fileFormat == null) {
      return;
    }

    List<GoogleDriveFile> l1sxFiles = GoogleDriveService
        .listAllSpreadsheetsFiles(driveId, fileFormat);

    for (GoogleDriveFile file : l1sxFiles) {
      FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
      String fileId = file.getFileId();
      String fileModifiedTime = file.getModifiedTime();

      FileImported lastImportedFile = fileImportedRepository.findByFileID(fileId);
      if (lastImportedFile == null) {//file chua import
        log.info("L1SX: Detected new file : " + file.getFileName());
        saveNewFile(newFile, sheetNames);
      } else {
        newFile.setId(lastImportedFile.getId());
        String lastImportModifiedTime = lastImportedFile.getModifiedTime();

        //file co thay doi
        if (!fileModifiedTime.equals(lastImportModifiedTime)) {
          log.info("L1SX: Data from file : " + file.getFileName() + " has been modified");
          deleteFileBySheetsId(driveId, fileId);
          log.info("L1SX: Deleted all old data of sheet: " + file.getFileName());
          saveNewFile(newFile, sheetNames);
        } else {
          log.info("L1SX: Data from file : " + file.getFileName() + " has been imported");
        }
      }
    }

  }

  private void importData(String spreadsheetId, String sheetName, int typeOfData)
      throws IOException, QTTTException {
    List<L1SX> l1sxList = L1SXUtil
        .getAllDataFromFile(sheetsService, spreadsheetId, sheetName, typeOfData);
    this.save(l1sxList);
  }

  @Override
  public void importData(String spreadsheetId, String sheetName) throws IOException {

  }

  private void deleteFileBySheetsId(String driveId, String fileId) {
    int dataType = getTypeConstants(driveId);
    switch (dataType) {
      case (TypeConstants.THUONG):
        l1SXRepository.deleteAllBySheetsIdThuong(fileId);
        break;
      case (TypeConstants.VIP):
        l1SXRepository.deleteAllBySheetsIdVip(fileId);
        break;
      case (TypeConstants.CS18):
        l1SXRepository.deleteAllBySheetsIdCS18(fileId);
        break;
      case (TypeConstants.D500):
        l1SXRepository.deleteAllBySheetsIdD500(fileId);
        break;
      case (TypeConstants.DUAN):
        l1SXRepository.deleteAllBySheetsIdDuan(fileId);
        break;
      default:
        break;
    }
  }

  private void saveNewFile(FileImported newFile, List<String> sheetNames) throws IOException {
    String fileId = newFile.getFileId();
    String driveId = newFile.getDriveId();
    log.info("Importing file: " + newFile.getFileName());

    for (String sheetName : sheetNames) {
      try {
        int dataType = getTypeConstants(driveId);
        if (dataType < 0) {
          return;
        }

        importData(fileId, sheetName, dataType);
      } catch (IOException e) {
        log.error("Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
        return;
      } catch (QTTTException e) {
        log.info(e.getMessage());
        return;
      }
    }

    log.info("Imported file: " + newFile.getFileName() + " status: ok");
    fileImportedRepository.save(newFile);
  }

  @Override
  public L1SX save(L1SX object) {
    return l1SXRepository.save(object);
  }

  @Override
  public void save(List<L1SX> objects) {
    l1SXRepository.saveAll(objects);
  }

  @Override
  public L1SX findById(Long aLong) {
    return l1SXRepository.getOne(aLong);
  }

  @Override
  public List<L1SX> findAll() {
    return l1SXRepository.findAll();
  }

  private String getFileFormat(String driveId) {
    if (driveId.equals(l1sxDriveIdThuong)) {
      return L1SXConstants.FILE_FORMAT_THUONG;
    }
    if (driveId.equals(l1sxDriveIdVip)) {
      return L1SXConstants.FILE_FORMAT_VIP;
    }
    if (driveId.equals(l1sxDriveIdCS18)) {
      return L1SXConstants.FILE_FORMAT_CS18;
    }
    if (driveId.equals(l1sxDriveIdD500)) {
      return L1SXConstants.FILE_FORMAT_D500;
    }
    if (driveId.equals(l1sxDriveIdDuan)) {
      return L1SXConstants.FILE_FORMAT_DA;
    }

    return null;
  }

  private int getTypeConstants(String driveId) {
    if (driveId.equals(l1sxDriveIdThuong)) {
      return TypeConstants.THUONG;
    }
    if (driveId.equals(l1sxDriveIdVip)) {
      return TypeConstants.VIP;
    }
    if (driveId.equals(l1sxDriveIdD500)) {
      return TypeConstants.D500;
    }
    if (driveId.equals(l1sxDriveIdCS18)) {
      return TypeConstants.CS18;
    }
    if (driveId.equals(l1sxDriveIdDuan)) {
      return TypeConstants.DUAN;
    }

    return -1;
  }
}
