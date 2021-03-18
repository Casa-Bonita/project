package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Payment;

import java.util.List;

public interface PaymentDAO {

    public List<Payment> getAllPayments();

    public void savePayment(Payment payment);

    public Payment getPayment(int id);

    public void deletePayment(int id);
}
