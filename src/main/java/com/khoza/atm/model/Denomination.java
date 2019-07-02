
package com.khoza.atm.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DENOMINATION")
public class Denomination
{

  @Id
  @Column (name = "DENOMINATION_ID")
  private int denominationId;
  @Column (name = "VALUE")
  private double value;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "DENOMINATION_TYPE_CODE")
  private DenominationType denominationTypeCode;

  public int getDenominationId()
  {
    return denominationId;
  }

  public void setDenominationId(int denominationId)
  {
    this.denominationId = denominationId;
  }

  public double getValue()
  {
    return value;
  }

  public void setValue(double value)
  {
    this.value = value;
  }

  public DenominationType getDenominationTypeCode()
  {
    return denominationTypeCode;
  }

  public void setDenominationTypeCode(DenominationType denominationTypeCode)
  {
    this.denominationTypeCode = denominationTypeCode;
  }
}