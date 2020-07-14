package vn.topica.datashine.spreadsheetscrawler.services.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.datashine.spreadsheetscrawler.models.FileImported;
import vn.topica.datashine.spreadsheetscrawler.repository.FileImportedRepository;
import vn.topica.datashine.spreadsheetscrawler.services.iservice.FileImportedService;

@Service
@Slf4j
public class FileImportedServiceImpl implements FileImportedService {

  @Autowired
  FileImportedRepository fileImportedRepository;

  @Override
  public FileImported save(FileImported object) {
    return fileImportedRepository.save(object);
  }

  @Override
  public void save(List<FileImported> files) {
    for (FileImported file : files) {
      String fileId = file.getFileId();
      FileImported fileInDB = fileImportedRepository.findByFileID(fileId);

      if (fileInDB != null){
        file.setId(fileInDB.getId());
      }
    }
    fileImportedRepository.saveAll(files);
  }

  @Override
  public FileImported findById(Long aLong) {
    return fileImportedRepository.getOne(aLong);
  }

  @Override
  public List<FileImported> findAll() {
    return fileImportedRepository.findAll();
  }

  @Override
  public FileImported findByFileId(String fileId) {
    return fileImportedRepository.findByFileID(fileId);
  }
}
