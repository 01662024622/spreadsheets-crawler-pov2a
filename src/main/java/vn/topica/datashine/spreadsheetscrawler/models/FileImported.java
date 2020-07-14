package vn.topica.datashine.spreadsheetscrawler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fileimported")
public class FileImported {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String fileId;

  @Column
  private String fileName;

  @Column
  private String driveId;

  @Column
  private String createdTime;

  @Column
  private String modifiedTime;

}
