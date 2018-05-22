
package com.test.seedproject;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.test.seedproject.util.DateUtil;



@Component("currentDateTimeService")
public class CurrentDateTimeService {

  public LocalDateTime getCurrentDateAndTime() {
    return LocalDateTime.now();
  }

  public String getFormattedCurrentDateAndTime() {
    return DateUtil.getFormatteDateTime(LocalDateTime.now());
  }

}
