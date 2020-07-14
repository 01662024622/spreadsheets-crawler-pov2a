package vn.topica.datashine.spreadsheetscrawler.models.tic;

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
public class TIC {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String hoTen;
  private String sdt;
  private String email;
  private String trangThaiCs;
  private String trangThaiGoi;
  private String level;

  @Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayDk;
  private String kenh;
  
  @Column(columnDefinition="TEXT")
  private String mauQc;
  private String tuKhoa;

  @Column(columnDefinition = "TEXT")
  private String ghiChu;

//  @Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayBgTvts;
  private String tvts;
  
  @Column(columnDefinition = "TEXT")
  private String nghiemThu;
  private Long soCall;

  @Column(columnDefinition = "TEXT")
  private String noiDungCall;

 // @Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayGoiGanNhat;

//  @Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayHenGoiLai;
  private String kho;

 // @Column(columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ngayBgQttt;
  private String ngayTic;
  private String flowNhanBg;
  private String type;
  private String gc1;
  private String gc2;
  private String gc3;
  private String spreadsheetsId;
}
