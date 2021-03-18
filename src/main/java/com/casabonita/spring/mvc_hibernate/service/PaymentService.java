package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Payment;

import java.util.List;

public interface PaymentService {

    public List<Payment> getAllPayments();

    public void savePayment(Payment payment);

    public Payment getPayment(int id);

    public void deletePaymetn(int id);
}
