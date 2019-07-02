package com.khoza.atm.model;

import javax.persistence.*;

@Entity
@Table(name = "ATM_ALLOCATION")
public class ATMAllocation {

    @Id
    @Column (name="ATM_ALLOCATION_ID")
    private int atmAllocationID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ATM_ID", nullable = false, referencedColumnName = "ATM_ID")
    private ATM atmId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DENOMINATION_ID", nullable = false, referencedColumnName = "DENOMINATION_ID")
    private Denomination denominationID;

    @Column (name = "COUNT")
    private int count;

    public int getAtmAllocationID() {
        return atmAllocationID;
    }

    public void setAtmAllocationID(int atmAllocationID) {
        this.atmAllocationID = atmAllocationID;
    }

    public ATM getAtmId() {
        return atmId;
    }

    public void setAtmId(ATM atmId) {
        this.atmId = atmId;
    }

    public Denomination getDenominationID() {
        return denominationID;
    }

    public void setDenominationID(Denomination denominationID) {
        this.denominationID = denominationID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
