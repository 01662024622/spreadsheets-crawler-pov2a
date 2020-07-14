package vn.topica.datashine.spreadsheetscrawler.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.topica.datashine.spreadsheetscrawler.models.re.RE;

@Repository
public interface RERepository extends JpaRepository<RE, Long> {

  @Modifying
  @Transactional
  @Query("DELETE FROM RE re WHERE re.spreadsheetsId = :spreadsheetsId")
  void deleteAllBySheetsId(@Param("spreadsheetsId") String spreadsheetsId);
}
