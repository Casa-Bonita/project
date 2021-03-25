package com.casabonita.spring.mvc_hibernate.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="renter")
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
//    @Size(min=3, max=50, message="Renter name must be at least 3 and no more than 50 characters long")
//    @NotBlank(message="Renter name is required field")
    private String name;

    @Column(name="ogrn")
//    @Size(min=13, max=13, message="Renter OGRN must be 13 characters long")
    private String ogrn;

    @Column(name="inn")
//    @Pattern(regexp="^[A-Za-z0-9]{3}(\\d?){2}[A-Za-z0-9]{3}$", message="Please use pattern XXXXPPXXX, where:" +
//            " X - any word character or number, and PP - a number in the range 0-99.")
    private String inn;

    @Column(name="registr_date")
//    @Pattern(regexp="^(((19){1})|((20){1}))\\d{2}-((0{1}[1-9]{1})|(1{1}[0-2]{1}))-((0{1}[1-9]{1})|((1|2){1}[0-9]{1})|(3{1}[0-1]{1}))$",
//            message="Please use pattern YYYY-MM-DD")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registrDate;

    @Column(name="address")
    private String address;

    @Column(name="director_name")
    private String directorName;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="phone")
//    @Pattern(regexp="^(\\+7\\()\\d{3}\\)\\d{3}(-\\d{2}){2}$", message="Please use pattern +7(XXX)XXX-XX-XX")
    private String phoneNumber;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "renter")
    private List<Contract> contractList;

    public Renter() {
    }

    public Renter(String name, String ogrn, String inn, Date registrDate, String address, String directorName,
                  String contactName, String phoneNumber) {
        this.name = name;
        this.ogrn = ogrn;
        this.inn = inn;
        this.registrDate = registrDate;
        this.address = address;
        this.directorName = directorName;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public void addContractToRenter(Contract contract){
        if(contractList == null){
            contractList = new ArrayList<>();
        }
        contractList.add(contract);
        contract.setRenter(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Date getRegistrDate() {
        return registrDate;
    }

    public void setRegistrDate(Date registrDate) {
        this.registrDate = registrDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}
