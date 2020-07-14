package vn.topica.datashine.spreadsheetscrawler.models.tonkho;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class TonKho {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tkId;
  private String name;
  private String sdt;
  private String email;
  private String kenh;
  private String level;
  private String kho;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayDk;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayCutoff;

  private String gc1;
  private String gc2;
  private String gc3;
  private String spreadsheetsId;
}
