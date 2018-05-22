package com.test.seedproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class Country.
 */
@Entity
@Table(name = "country",
    indexes = {@Index(name = "country_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "country_id_index", unique = true, columnList = "uuid"),
        @Index(name = "country_name_index", unique = true, columnList = "country_name"),
        @Index(name = "country_2_code_index", unique = false, columnList = "country_2_code"),
        @Index(name = "country_3_code_index", unique = false, columnList = "country_3_code")})
public class Country extends BaseDomain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id = 0;

  /** The country name. */
  @Column(name = "country_name", nullable = false)
  private String countryName = StringUtils.EMPTY;

  /** The country name. */
  @Column(name = "country_2_code", length = 2)
  private String country2Code = StringUtils.EMPTY;

  /** The country 3 code. */
  @Column(name = "country_3_code", length = 3)
  private String country3Code = StringUtils.EMPTY;

  /** The calling code. */
  @Column(name = "calling_code", length = 5)
  private String callingCode;

  /**
   * Gets the id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(long id) {
    this.id = id;
  }



  /**
   * Gets the country name.
   *
   * @return the country name
   */
  public String getCountryName() {
    return countryName;
  }

  /**
   * Sets the country name.
   *
   * @param countryName the new country name
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  /**
   * Gets the serialversionuid.
   *
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  /**
   * Gets the country 2 code.
   *
   * @return the country 2 code
   */
  public String getCountry2Code() {
    return country2Code;
  }

  /**
   * Sets the country 2 code.
   *
   * @param country2Code the new country 2 code
   */
  public void setCountry2Code(String country2Code) {
    this.country2Code = country2Code;
  }

  /**
   * Gets the country 3 code.
   *
   * @return the country 3 code
   */
  public String getCountry3Code() {
    return country3Code;
  }

  /**
   * Sets the country 3 code.
   *
   * @param country3Code the new country 3 code
   */
  public void setCountry3Code(String country3Code) {
    this.country3Code = country3Code;
  }

  /**
   * Gets the calling code.
   *
   * @return the calling code
   */
  public String getCallingCode() {
    return callingCode;
  }

  /**
   * Sets the calling code.
   *
   * @param callingCode the new calling code
   */
  public void setCallingCode(String callingCode) {
    this.callingCode = callingCode;
  }



  /*
   * (non-Javadoc)
   * 
   * @see lk.pwc.projects.iecd.ls.entitymanagement.domain.core.BaseDomain#toString()
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }


}
