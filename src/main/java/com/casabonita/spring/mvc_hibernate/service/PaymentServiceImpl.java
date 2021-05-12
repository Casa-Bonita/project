package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final AccountDAO accountDAO;
    private final PaymentDAO paymentDAO;

    public PaymentServiceImpl(AccountDAO accountDAO, PaymentDAO paymentDAO) {
        this.accountDAO = accountDAO;
        this.paymentDAO = paymentDAO;
    }

    @Override
    @Transactional
    public List<Payment> getAllPayments() {

        return paymentDAO.getAllPayments();
    }

    @Override
    @Transactional
    public void savePayment(Payment payment, String accountNumber) {

        Account account = accountDAO.getAccountByNumber(accountNumber);

        payment.setAccount(account);

        paymentDAO.savePayment(payment);

    }

    @Override
    @Transactional
    public Payment getPayment(int id) {

        return paymentDAO.getPayment(id);
    }

    @Override
    @Transactional
    public void deletePayment(int id) {

        paymentDAO.deletePayment(id);

    }
}
