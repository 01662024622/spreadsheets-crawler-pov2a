package vn.topica.datashine.spreadsheetscrawler.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.constants.REConstants;
import vn.topica.datashine.spreadsheetscrawler.constants.TICConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.files.GoogleDriveFile;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.models.tic.TIC;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.repository.TICRepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.TICService;
import vn.topica.datashine.spreadsheetscrawler.utils.data.GoogleDriveFileUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.TICUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;

@Service
@Slf4j
public class TICServiceImpl implements TICService {

  private Sheets sheetsService;
  private FileImportedRepository fileImportedRepository;
  private TICRepository ticRepository;

  @Autowired
  public TICServiceImpl(TICRepository ticRepository,
      FileImportedRepository fileImportedRepository)
      throws GeneralSecurityException, IOException {
    this.ticRepository = ticRepository;
    this.fileImportedRepository = fileImportedRepository;
    this.sheetsService = SheetsServiceUtil.getSheetsService();
  }

  @Override
  public void importData(String driveId, List<String> sheetNames) {
    try {
      List<GoogleDriveFile> ticFiles = GoogleDriveService
          .listAllSpreadsheetsFiles(driveId, TICConstants.FILE_FORMAT);

      for (GoogleDriveFile file : ticFiles) {
        FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
        String fileId = file.getFileId();
        String fileModifiedTime = file.getModifiedTime();

        FileImported lastImportedFile = fileImportedRepository.findByFileID(fileId);
        if (lastImportedFile == null) {//file chua import
          log.info("TIC: Detected new file : " + file.getFileName());
          saveNewFile(newFile, sheetNames);
        } else {
          newFile.setId(lastImportedFile.getId());
          String lastImportModifiedTime = lastImportedFile.getModifiedTime();

          //file co thay doi
          if (!fileModifiedTime.equals(lastImportModifiedTime)) {
            log.info("TIC: Data from file : " + file.getFileName() + " has been modified");
            ticRepository.deleteAllBySheetsId(fileId);
            log.info("TIC: Deleted all old data of sheet: " + file.getFileName());
            saveNewFile(newFile, sheetNames);
          } else {
            log.info("TIC: Data from file : " + file.getFileName() + " has been imported");
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void importData(String spreadsheetId, String sheetName) throws IOException {
    List<TIC> ticList = null;
    try {
      ticList = TICUtil.getAllDataFromFile(sheetsService, spreadsheetId, sheetName);
    } catch (QTTTException e) {
      throw new IOException(e.getMessage());
    }
    this.save(ticList);
  }

  private void saveNewFile(FileImported newFile, List<String> sheetNames) {
    String fileId = newFile.getFileId();
    log.info("TIC: Importing file: " + newFile.getFileName());

    for (String sheetName : sheetNames) {
      try {
        importData(fileId, sheetName);
      } catch (IOException e) {
        log.error("TIC: Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
        return;
      }
    }

    log.info("TIC: Imported file: " + newFile.getFileName() + " status: ok");
    fileImportedRepository.save(newFile);
  }

  @Override
  public TIC save(TIC object) {
    return ticRepository.save(object);
  }

  @Override
  public void save(List<TIC> objects) {
    ticRepository.saveAll(objects);
  }

  @Override
  public TIC findById(Long aLong) {
    return ticRepository.getOne(aLong);
  }

  @Override
  public List<TIC> findAll() {
    return ticRepository.findAll();
  }
}
