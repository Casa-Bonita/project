package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="account_data")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @Column(name="payment")
    private int amount;

    @Column(name="payment_date")
    private Date date;

    @Column(name="payment_purpose")
    private String purpose;

    public Payment() {
    }

    public Payment(Account account, int amount, Date date, String purpose) {
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.purpose = purpose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
