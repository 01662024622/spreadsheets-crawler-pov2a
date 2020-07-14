package vn.topica.datashine.spreadsheetscrawler.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.topica.datashine.spreadsheetscrawler.models.c3b.C3B;

public interface C3BRepository extends JpaRepository<C3B, Long> {

//  @Modifying
//  @Transactional
//  @Query("DELETE FROM C3B c3b WHERE c3b.spreadsheetsId = :spreadsheetsId")
//  void deleteAllBySheetsId(@Param("spreadsheetsId") String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM c3b_thuong where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdThuong(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM c3b_vip where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdVip(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM c3b_d500 where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdD500(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM c3b_cs18 where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdCS18(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM c3b_duan where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdDuan(String spreadsheetsId);
}
