package com.test.seedproject.services.business;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.seedproject.domain.Country;
import com.test.seedproject.domain.dataaccessobjects.CountryRepository;
import com.test.seedproject.services.CountryService;


// TODO: Auto-generated Javadoc
/**
 * The Class CurrencyTypeServicesImpl.
 */
@Service
@Transactional(value = "transactionManager")
public class CountryServicesImpl implements CountryService {

  /** The country repository. */
  private CountryRepository countryRepository;


  /**
   * Sets the country repository.
   *
   * @param countryRepository the new country repository
   */
  @Autowired
  public void setCountryRepository(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
  @Override
  public List<Country> getActiveCountries() {
    return countryRepository.findByIsDeleted(false);
  }

  @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false)
  @Override
  public long createCountry(Country country) {
    return countryRepository.save(country).getId();
  }



}
