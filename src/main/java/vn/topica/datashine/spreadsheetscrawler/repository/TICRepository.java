package vn.topica.datashine.spreadsheetscrawler.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.topica.datashine.spreadsheetscrawler.models.tic.TIC;

@Repository
public interface TICRepository extends JpaRepository<TIC, Long> {
  @Modifying
  @Transactional
  @Query("DELETE FROM TIC tic WHERE tic.spreadsheetsId = :spreadsheetsId")
  void deleteAllBySheetsId(@Param("spreadsheetsId") String spreadsheetsId);

//  @Transactional
//  Long deleteAllBySpreadsheetsId(String spreadsheetsId);
}
