package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Payment;

import java.util.List;

public interface PaymentDAO {

    List<Payment> getAllPayments();

    void savePayment(Payment payment);

    Payment getPayment(int id);

    void deletePayment(int id);
}
