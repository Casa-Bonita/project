package com.casabonita.spring.mvc_hibernate.entity;

import java.util.Date;

public class Summary {

    private String placeName;
    private String renterName;
    private String contractNumber;
    private Date contractFinishDate;
    private int meterNumber;
    private int lastMeterData;
    private String accountNumber;
    private int totalPayments;

    public Summary() {
    }

    public Summary(String placeName, String renterName, String contractNumber, Date contractFinishDate,
                   int meterNumber, int lastMeterData, String accountNumber, int totalPayments) {
        this.placeName = placeName;
        this.renterName = renterName;
        this.contractNumber = contractNumber;
        this.contractFinishDate = contractFinishDate;
        this.meterNumber = meterNumber;
        this.lastMeterData = lastMeterData;
        this.accountNumber = accountNumber;
        this.totalPayments = totalPayments;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getContractFinishDate() {
        return contractFinishDate;
    }

    public void setContractFinishDate(Date contractFinishDate) {
        this.contractFinishDate = contractFinishDate;
    }

    public int getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(int meterNumber) {
        this.meterNumber = meterNumber;
    }

    public int getLastMeterData() {
        return lastMeterData;
    }

    public void setLastMeterData(int lastMeterData) {
        this.lastMeterData = lastMeterData;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
    }
}
