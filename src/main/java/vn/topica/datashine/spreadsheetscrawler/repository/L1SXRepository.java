package vn.topica.datashine.spreadsheetscrawler.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.topica.datashine.spreadsheetscrawler.models.l1sx.L1SX;

public interface L1SXRepository extends JpaRepository<L1SX, Long> {

//  @Modifying
//  @Transactional
//  @Query("DELETE FROM L1SX l1sx WHERE l1sx.spreadsheetsId = :spreadsheetsId")
//  void deleteAllBySheetsId(@Param("spreadsheetsId") String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM l1sx_thuong where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdThuong(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM l1sx_vip where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdVip(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM l1sx_d500 where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdD500(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM l1sx_cs18 where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdCS18(String spreadsheetsId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM l1sx_duan where spreadsheets_id = ?1", nativeQuery = true)
  void deleteAllBySheetsIdDuan(String spreadsheetsId);
}
