package com.casabonita.spring.mvc_hibernate.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="renter")
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    @Size(min=3, max=50, message="Renter name must be at least 3 and no more than 50 characters long")
    @NotBlank(message="Renter name is required field")
    private String renterName;

    @Column(name="ogrn")
    @Size(min=13, max=13, message="Renter name must be 13 characters long")
    private String renterOGRN;

    @Column(name="inn")
    @Pattern(regexp="^[A-Za-z0-9]{3}(\\d?){2}[A-Za-z0-9]{3}$", message="Please use pattern XXXXPPXXX, where:" +
            " X - any word character or number, and PP - a number in the range 0-99.")
    private String renterINN;

    @Column(name="registr_date")
    @Pattern(regexp="^(((19){1})|((20){1}))\\d{2}-((0{1}[1-9]{1})|(1{1}[0-2]{1}))-((0{1}[1-9]{1})|((1|2){1}[0-9]{1})|(3{1}[0-1]{1}))$",
            message="Please use pattern YYYY-MM-DD")
    private Date renterRegistrDate;

    @Column(name="address")
    private String renterAddress;

    @Column(name="director_name")
    private String renterDirector;

    @Column(name="contact_name")
    private String renterContactName;

    @Column(name="phone")
    @Pattern(regexp="^(\\+7\\()\\d{3}\\)\\d{3}(-\\d{2}){2}$", message="Please use pattern +7(XXX)XXX-XX-XX")
    private String renterPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contract_id")
    private Contract renterContract;

    private String dateStringFormat;

    public Renter() {
    }

//    public Renter(String renterName, String renterOGRN, String renterINN, String renterRegistrDate, String renterAddress,
//                  String renterDirector, String renterContactName, String renterPhone) {
//        this.renterName = renterName;
//        this.renterOGRN = renterOGRN;
//        this.renterINN = renterINN;
//        this.renterRegistrDate = renterRegistrDate;
//        this.renterAddress = renterAddress;
//        this.renterDirector = renterDirector;
//        this.renterContactName = renterContactName;
//        this.renterPhone = renterPhone;
//    }

    // Constructor witn Date registrDate
    public Renter(String renterName, String renterOGRN, String renterINN, Date renterRegistrDate, String renterAddress,
                  String renterDirector, String renterContactName, String renterPhone, Contract renterContract) {
        this.renterName = renterName;
        this.renterOGRN = renterOGRN;
        this.renterINN = renterINN;
        this.renterRegistrDate = renterRegistrDate;
        this.renterAddress = renterAddress;
        this.renterDirector = renterDirector;
        this.renterContactName = renterContactName;
        this.renterPhone = renterPhone;
        this.renterContract = renterContract;
    }

    // Constructor with String dateStringFormat
    public Renter(String renterName, String renterOGRN, String renterINN, String renterAddress, String renterDirector,
                  String renterContactName, String renterPhone, Contract renterContract, String dateStringFormat) {
        this.renterName = renterName;
        this.renterOGRN = renterOGRN;
        this.renterINN = renterINN;
        this.renterAddress = renterAddress;
        this.renterDirector = renterDirector;
        this.renterContactName = renterContactName;
        this.renterPhone = renterPhone;
        this.renterContract = renterContract;
        this.dateStringFormat = dateStringFormat;
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

    public Date getRenterRegistrDate() {
        return renterRegistrDate;
    }

    public void setRenterRegistrDate(Date renterRegistrDate) {
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

    public String getDateStringFormat() {
        return dateStringFormat;
    }

    public void setDateStringFormat(String dateStringFormat) {
        this.dateStringFormat = dateStringFormat;
    }
}
