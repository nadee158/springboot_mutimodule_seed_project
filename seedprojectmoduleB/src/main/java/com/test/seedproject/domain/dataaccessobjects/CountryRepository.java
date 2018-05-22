/*
 * @author nsenevirat001 - PwC
 */
package com.test.seedproject.domain.dataaccessobjects;

import java.util.List;

import com.test.seedproject.domain.Country;

public interface CountryRepository extends BaseRepository<Country, Long> {

  public List<Country> findByCountry2CodeOrCountry3Code(String country2Code, String country3Code);

  public List<Country> findByIsDeleted(boolean isDeleted);

  public List<Country> findByCountry2Code(String country2Code);


}
