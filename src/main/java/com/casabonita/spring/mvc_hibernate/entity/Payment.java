package com.casabonita.spring.mvc_hibernate.entity;

import javax.persistence.*;

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
    private int paymentAmount;

    @Column(name="payment_date")
    private String paymentDate;

    @Column(name="payment_purpose")
    private String paymentPurpose;

    public Payment() {
    }

    public Payment(int paymentAmount, String paymentDate, String paymentPurpose) {
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentPurpose = paymentPurpose;
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

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }
}
