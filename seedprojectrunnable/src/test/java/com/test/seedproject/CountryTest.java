package com.test.seedproject;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.seedproject.domain.Country;
import com.test.seedproject.services.CountryService;

public class CountryTest extends AbstractTest {

  @Autowired
  private CountryService countryService;

  @Test
  public void testCreateCountry() {
    Country country = getNewCountry();
    long id = this.countryService.createCountry(country);
    Assert.assertNotEquals(0, id);
  }

  private Country getNewCountry() {
    Country country = new Country();
    country.setCallingCode("+94");
    country.setCountry2Code("SL");
    country.setCountry3Code("SLK");
    country.setCountryName("Sri Lanka");
    return country;
  }

}
