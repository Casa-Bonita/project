package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="account_number")
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contract_id")
    private Contract accountContract;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "account")
    private List<Payment> paymentsList;

    public Account() {
    }

    public Account(String number, Contract accountContract) {
        this.number = number;
        this.accountContract = accountContract;
    }

    public void addPaymentToAccount(Payment payment){
        if(paymentsList == null){
            paymentsList = new ArrayList<>();
        }
        paymentsList.add(payment);
        payment.setAccount(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contract getAccountContract() {
        return accountContract;
    }

    public void setAccountContract(Contract accountContract) {
        this.accountContract = accountContract;
    }

    public List<Payment> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payment> paymentsList) {
        this.paymentsList = paymentsList;
    }
}
