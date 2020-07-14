package vn.topica.datashine.spreadsheetscrawler.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.constants.TonKhoConstants;
import vn.topica.datashine.spreadsheetscrawler.exceptions.QTTTException;
import vn.topica.datashine.spreadsheetscrawler.files.GoogleDriveFile;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.models.tonkho.TonKho;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.repository.TonKhoRepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.TonKhoService;
import vn.topica.datashine.spreadsheetscrawler.utils.data.GoogleDriveFileUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.data.TonKhoUtil;
import vn.topica.datashine.spreadsheetscrawler.utils.googleapi.SheetsServiceUtil;

@Service
@Slf4j
public class TonKhoServiceImpl implements TonKhoService {

  private Sheets sheetsService;
  private FileImportedRepository fileImportedRepository;
  private TonKhoRepository tonKhoRepository;

  @Autowired
  public TonKhoServiceImpl(TonKhoRepository tonKhoRepository,
      FileImportedRepository fileImportedRepository)
      throws GeneralSecurityException, IOException {
    this.tonKhoRepository = tonKhoRepository;
    this.fileImportedRepository = fileImportedRepository;
    this.sheetsService = SheetsServiceUtil.getSheetsService();
  }

  @Override
  public void importData(String driveId, List<String> sheetNames) {
    try {
      List<GoogleDriveFile> tonkhoFiles = GoogleDriveService
          .listAllSpreadsheetsFiles(driveId, TonKhoConstants.FILE_FORMAT);

      for (GoogleDriveFile file : tonkhoFiles) {
        FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
        String fileId = file.getFileId();
        String fileModifiedTime = file.getModifiedTime();

        FileImported lastImportedFile = fileImportedRepository.findByFileID(fileId);
        if (lastImportedFile == null) {//file chua import
          log.info("Ton kho: Detected new file : " + file.getFileName());
          saveNewFile(newFile, sheetNames);
        } else {
          newFile.setId(lastImportedFile.getId());
          String lastImportModifiedTime = lastImportedFile.getModifiedTime();

          //file co thay doi
          if (!fileModifiedTime.equals(lastImportModifiedTime)) {
            log.info("Ton kho: Data from file : " + file.getFileName() + " has been modified");
            tonKhoRepository.deleteAllBySheetsId(fileId);
            log.info("Ton kho: Deleted all old data of sheet: " + file.getFileName());
            saveNewFile(newFile, sheetNames);
          } else {
            log.info("Ton kho: Data from file : " + file.getFileName() + " has been imported");
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void importData(String spreadsheetId, String sheetName) throws IOException {
    List<TonKho> tonKhoList = null;
    try {
      tonKhoList = TonKhoUtil
          .getAllDataFromFile(sheetsService, spreadsheetId, sheetName);
    } catch (QTTTException e) {
      throw new IOException(e.getMessage());
    }
    this.save(tonKhoList);
  }

  private void saveNewFile(FileImported newFile, List<String> sheetNames) {
    String fileId = newFile.getFileId();
    String driveId = newFile.getDriveId();
    log.info("Ton kho: Importing file: " + newFile.getFileName());

    for (String sheetName : sheetNames) {
      try {
        importData(fileId, sheetName);
      } catch (IOException e) {
        log.error(
            "Ton kho: Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
        return;
      }
    }

    log.info("Ton kho: Imported file: " + newFile.getFileName() + " status: ok");
    fileImportedRepository.save(newFile);
  }

  @Override
  public TonKho save(TonKho object) {
    return tonKhoRepository.save(object);
  }

  @Override
  public void save(List<TonKho> objects) {
    tonKhoRepository.saveAll(objects);
  }

  @Override
  public TonKho findById(Long aLong) {
    return tonKhoRepository.getOne(aLong);
  }

  @Override
  public List<TonKho> findAll() {
    return tonKhoRepository.findAll();
  }
}
