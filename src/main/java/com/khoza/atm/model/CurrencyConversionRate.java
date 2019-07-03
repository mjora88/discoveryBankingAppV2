package com.khoza.atm.model;


import javax.persistence.*;

@Entity
@Table(name = "Currency_conversion_Rate")
public class CurrencyConversionRate {

  @Id
  @Column(name = "CURRENCY_CODE")
  private String currencyCode;

  @Column
  private String conversionIndicator;

  @Column (name = "RATE")
  private String rate;

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getConversionIndicator() {
    return conversionIndicator;
  }

  public void setConversionIndicator(String conversionIndicator) {
    this.conversionIndicator = conversionIndicator;
  }

  public String getRate() {
    return rate;
  }

  public void setRateDecimal(String rate) {
    this.rate = rate;
  }
}