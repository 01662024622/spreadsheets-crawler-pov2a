package vn.topica.datashine.spreadsheetscrawler.models.c3b;

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

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class C3B {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "C3B_SEQ")
  @SequenceGenerator(name = "C3B_SEQ", sequenceName = "SEQUENCE_C3B")
  private Long id;
  private String sanPham;

  @Temporal(TemporalType.TIMESTAMP)
  private Date submitTime;
  private String hoTen;
  private String phone;
  private String email;
  private Long tuoi;
  private String contactChannel;

  @Column(columnDefinition = "text")
  private String adsLink;
  
  @Column(columnDefinition = "text")
  private String utmSource;
  private String utmTeam;
  private String utmTerm;
  private String utmMedium;
  private String utmAgent;
  private String utmCampaign;

  @Column(columnDefinition = "text")
  private String utmContent;
  private String utmFlow;
  private String gc1;
  private String gc2;
  private String gc3;
  private String spreadsheetsId;
}
