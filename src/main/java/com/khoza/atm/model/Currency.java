
package com.khoza.atm.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name ="CURRENCY" )
public class Currency implements Serializable {

  @Id
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CURRENCY_CODE", nullable = false, referencedColumnName = "CURRENCY_CODE")
  private CurrencyConversionRate currencyCode;

  @Column(name = "DECIMAL_PLACES")
  private int decimalPlaces;
  @Column (name = "DESCRIPTION")
  private String description;

  public CurrencyConversionRate getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(CurrencyConversionRate currencyCode) {
    this.currencyCode = currencyCode;
  }

  public int getDecimalPlaces() {
    return decimalPlaces;
  }

  public void setDecimalPlaces(int decimalPlaces) {
    this.decimalPlaces = decimalPlaces;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
