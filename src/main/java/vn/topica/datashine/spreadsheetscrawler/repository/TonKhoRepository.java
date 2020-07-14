package vn.topica.datashine.spreadsheetscrawler.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.topica.datashine.spreadsheetscrawler.models.tonkho.TonKho;

@Repository
public interface TonKhoRepository extends JpaRepository<TonKho, Long> {

  @Modifying
  @Transactional
  @Query("DELETE FROM TonKho tonkho WHERE tonkho.spreadsheetsId = :spreadsheetsId")
  void deleteAllBySheetsId(@Param("spreadsheetsId") String spreadsheetsId);
}
