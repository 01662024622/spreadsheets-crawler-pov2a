package vn.topica.datashine.spreadsheetscrawler.services.base;

import java.util.List;

public interface BaseService<T, ID>{

  T save(T object);

  void save(List<T> objects);

  T findById(ID id);

  List<T> findAll();

}
