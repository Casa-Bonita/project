package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Payment;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    @Transactional
    public List<Payment> getAllPayments() {

        return paymentDAO.getAllPayments();
    }

    @Override
    @Transactional
    public void savePayment(Payment payment) {

        paymentDAO.savePayment(payment);

    }

    @Override
    @Transactional
    public Payment getPayment(int id) {

        return paymentDAO.getPayment(id);
    }

    @Override
    @Transactional
    public void deletePaymetn(int id) {

        paymentDAO.deletePayment(id);

    }
}
