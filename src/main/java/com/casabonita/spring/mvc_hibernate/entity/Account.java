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
    private String accountNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contract_id")
    private Contract accountContract;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "account")
    private List<Payment> payments;

    public Account() {
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void addPaymentToAccount(Payment payment){
        if(payments == null){
            payments = new ArrayList<>();
        }
        payments.add(payment);
        payment.setAccount(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Contract getAccountContract() {
        return accountContract;
    }

    public void setAccountContract(Contract accountContract) {
        this.accountContract = accountContract;
    }
}
