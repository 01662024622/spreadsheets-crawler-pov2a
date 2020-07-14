package vn.topica.datashine.spreadsheetscrawler.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.constants.REConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.files.GoogleDriveFile;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.models.re.RE;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.repository.RERepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.REService;
import vn.topica.datashine.spreadsheetscrawler.utils.data.GoogleDriveFileUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.REUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;

@Service
@Slf4j
public class REServiceImpl implements REService {

  private Sheets sheetsService;
  private FileImportedRepository fileImportedRepository;
  private RERepository reRepository;

  @Autowired
  public REServiceImpl(RERepository RERepository,
      FileImportedRepository fileImportedRepository)
      throws GeneralSecurityException, IOException {
    this.reRepository = RERepository;
    this.fileImportedRepository = fileImportedRepository;
    this.sheetsService = SheetsServiceUtil.getSheetsService();
  }

  @Override
  public void importData(String driveId, List<String> sheetNames) {
    try {
      List<GoogleDriveFile> reFiles = GoogleDriveService
          .listAllSpreadsheetsFiles(driveId, REConstants.FILE_FORMAT);

      for (GoogleDriveFile file : reFiles) {
        FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
        String fileId = file.getFileId();
        String fileModifiedTime = file.getModifiedTime();

        FileImported lastImportedFile = fileImportedRepository.findByFileID(fileId);
        if (lastImportedFile == null) {//file chua import
          log.info("RE: Detected new file : " + file.getFileName());
          saveNewFile(newFile, sheetNames);
        } else {
          newFile.setId(lastImportedFile.getId());
          String lastImportModifiedTime = lastImportedFile.getModifiedTime();

          //file co thay doi
          if (!fileModifiedTime.equals(lastImportModifiedTime)) {
            log.info("RE: Data from file : " + file.getFileName() + " has been modified");
            reRepository.deleteAllBySheetsId(fileId);
            log.info("RE: Deleted all old data of sheet: " + file.getFileName());
            saveNewFile(newFile, sheetNames);
          } else {
            log.info("RE: Data from file : " + file.getFileName() + " has been imported");
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void importData(String spreadsheetId, String sheetName) throws IOException {
    List<RE> reList = null;
    try {
      reList = REUtil.getAllDataFromFile(sheetsService, spreadsheetId, sheetName);
    } catch (QTTTException e) {
      throw new IOException(e.getMessage());
    }
    this.save(reList);
  }

  private void saveNewFile(FileImported newFile, List<String> sheetNames) {
    String fileId = newFile.getFileId();
    log.info("RE: Importing file: " + newFile.getFileName());

    for (String sheetName : sheetNames) {
      try {
        importData(fileId, sheetName);
      } catch (IOException e) {
        log.error("RE: Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
        return;
      }
    }

    log.info("RE: Imported file: " + newFile.getFileName() + " status: ok");
    fileImportedRepository.save(newFile);
  }

  @Override
  public RE save(RE object) {
    return reRepository.save(object);
  }

  @Override
  public void save(List<RE> objects) {
    reRepository.saveAll(objects);
  }

  @Override
  public RE findById(Long aLong) {
    return reRepository.getOne(aLong);
  }

  @Override
  public List<RE> findAll() {
    return reRepository.findAll();
  }
}
