package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="number")
    private String contractNumber;

    @Column(name="contract_date")
    private String contractDate;

    @Column(name="fare")
    private int contractFare;

    @Column(name="start_date")
    private String contractStart;

    @Column(name="finish_date")
    private String contractFinish;

    @Column(name="payment_day")
    private int contractPaymentDay;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="place_id")
    private Place contractPlace;

    @OneToOne(mappedBy="renterContract", cascade = CascadeType.ALL)
    private Renter renter;

    @OneToOne(mappedBy="accountContract", cascade = CascadeType.ALL)
    private Account account;

    public Contract() {
    }

    public Contract(String contractNumber, String contractDate, int contractFare, String contractStart,
                    String contractFinish, int contractPaymentDay) {
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.contractFare = contractFare;
        this.contractStart = contractStart;
        this.contractFinish = contractFinish;
        this.contractPaymentDay = contractPaymentDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public int getContractFare() {
        return contractFare;
    }

    public void setContractFare(int contractFare) {
        this.contractFare = contractFare;
    }

    public String getContractStart() {
        return contractStart;
    }

    public void setContractStart(String contractStart) {
        this.contractStart = contractStart;
    }

    public String getContractFinish() {
        return contractFinish;
    }

    public void setContractFinish(String contractFinish) {
        this.contractFinish = contractFinish;
    }

    public int getContractPaymentDay() {
        return contractPaymentDay;
    }

    public void setContractPaymentDay(int contractPaymentDay) {
        this.contractPaymentDay = contractPaymentDay;
    }

    public Place getContractPlace() {
        return contractPlace;
    }

    public void setContractPlace(Place contractPlace) {
        this.contractPlace = contractPlace;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
