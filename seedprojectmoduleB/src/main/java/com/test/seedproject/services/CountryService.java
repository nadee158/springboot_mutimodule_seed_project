package com.test.seedproject.services;

import java.util.List;

import com.test.seedproject.domain.Country;

public interface CountryService {

  public List<Country> getActiveCountries();

  public long createCountry(Country country);

}
