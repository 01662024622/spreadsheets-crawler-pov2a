package vn.topica.datashine.spreadsheetscrawler.models.re;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class RE {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayBgl8;
  private String emailBg;
  private String sanPham;
  private String name;
  private String sdt;
  private String email;
  private Double thucThu;
  private String htThanhtoan;
  private Double giatritaikhoan;
  private Long thoigianhoc;
  private String nguoncts;
  private String loaihocvien;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngaytienve;
  private String loaitaikhoan;

  @Column(columnDefinition="TEXT")
  private String khuyenmai;
  private String contactid;
  private String makhoahoc;
  private String kenhcrm;
  private String tvtscrm;
  private String flowtheotvts;
  private String kenhquidoi;

  //@Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngaydangky;
  private String xacnhantienve;
  private String gc1;
  private String gc2;
  private String gc3;
  private String spreadsheetsId;
}
