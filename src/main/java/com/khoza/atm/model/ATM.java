package com.khoza.atm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATM")
public class ATM
{

  @Id
  @Column(name = "ATM_ID")
  private int atmID;
  @Column(name = "NAME")
  private String name;
  @Column(name = "LOCATION")
  private String location;

  public int getAtmAllocationID()
  {
    return atmID;
  }

  public void setAtmAllocationID(int atmAllocationID)
  {
    this.atmID = atmAllocationID;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  @Override
  public String toString()
  {
    return atmID + ".\t" + name + "\t Location=" + location;
  }
}
