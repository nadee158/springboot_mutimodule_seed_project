package com.test.seedproject.domain.dataaccessobjects;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

  // here CRUD repository is added, this can be changed to PagingAndSortingRepository, or any such
  // according to requirement

}
