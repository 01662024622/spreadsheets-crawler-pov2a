package vn.topica.datashine.spreadsheetscrawler.models.l1bg;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class L1BG {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "L1BG_SEQ")
  @SequenceGenerator(name = "L1BG_SEQ", sequenceName = "SEQUENCE_L1BG")
  private Long id;

  private String sanPham;
  private String name;
  private String phone;
  private String email;
  private String kenh;
  private String level;
  private String kho;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngaySx;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayBg;
  private String luong;
  private String luongNhanBg;
  private String gc1;
  private String gc2;
  private String gc3;
  private String spreadsheetsId;
}
