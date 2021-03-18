package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="renter")
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String renterName;

    @Column(name="ogrn")
    private String renterOGRN;

    @Column(name="inn")
    private String renterINN;

    @Column(name="registr_date")
    private String renterRegistrDate;

    @Column(name="address")
    private String renterAddress;

    @Column(name="director_name")
    private String renterDirector;

    @Column(name="contact_name")
    private String renterContactName;

    @Column(name="phone")
    private String renterPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contract_id")
    private Contract renterContract;

    public Renter() {
    }

    public Renter(String renterName, String renterOGRN, String renterINN, String renterRegistrDate,
                  String renterAddress, String renterDirector, String renterContactName, String renterPhone) {
        this.renterName = renterName;
        this.renterOGRN = renterOGRN;
        this.renterINN = renterINN;
        this.renterRegistrDate = renterRegistrDate;
        this.renterAddress = renterAddress;
        this.renterDirector = renterDirector;
        this.renterContactName = renterContactName;
        this.renterPhone = renterPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterOGRN() {
        return renterOGRN;
    }

    public void setRenterOGRN(String renterOGRN) {
        this.renterOGRN = renterOGRN;
    }

    public String getRenterINN() {
        return renterINN;
    }

    public void setRenterINN(String renterINN) {
        this.renterINN = renterINN;
    }

    public String getRenterRegistrDate() {
        return renterRegistrDate;
    }

    public void setRenterRegistrDate(String renterRegistrDate) {
        this.renterRegistrDate = renterRegistrDate;
    }

    public String getRenterAddress() {
        return renterAddress;
    }

    public void setRenterAddress(String renterAddress) {
        this.renterAddress = renterAddress;
    }

    public String getRenterDirector() {
        return renterDirector;
    }

    public void setRenterDirector(String renterDirector) {
        this.renterDirector = renterDirector;
    }

    public String getRenterContactName() {
        return renterContactName;
    }

    public void setRenterContactName(String renterContactName) {
        this.renterContactName = renterContactName;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public void setRenterPhone(String renterPhone) {
        this.renterPhone = renterPhone;
    }

    public Contract getRenterContract() {
        return renterContract;
    }

    public void setRenterContract(Contract renterContract) {
        this.renterContract = renterContract;
    }
}
