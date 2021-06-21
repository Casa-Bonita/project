package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Payment;

import java.util.List;

public interface PaymentDAO {

    List<Payment> getAllPayments();

    void savePayment(Payment payment);

    Payment getPayment(Integer id);

    void deletePaymentById(Integer id);

    void deletePaymentByAccountId(Integer id);

}
