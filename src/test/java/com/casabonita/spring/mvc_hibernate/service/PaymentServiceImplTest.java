package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class PaymentServiceImplTest {

    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
    PaymentDAO paymentDAO = Mockito.mock(PaymentDAO.class);

    PaymentService paymentService = new PaymentServiceImpl(accountDAO, paymentDAO);

    @Test
    public void shouldReturnAllPayments() {

        List<Payment> expected = List.of(new Payment());

        Mockito.when(paymentDAO.getAllPayments()).thenReturn(expected);

        List<Payment> actual = paymentService.getAllPayments();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSavePayment() {

        Payment paymentExpected = new Payment();
        Account accountExpected = new Account();
        String accountNumber = "TestAccount";
        accountExpected.setNumber(accountNumber);

        ArgumentCaptor<Payment> requestCaptor = ArgumentCaptor.forClass(Payment.class);
        Mockito.when(accountDAO.getAccountByNumber(accountNumber)).thenReturn(accountExpected);

        paymentService.savePayment(paymentExpected, accountNumber);

        Mockito.verify(paymentDAO, times(1)).savePayment(requestCaptor.capture());
        Payment paymentActual = requestCaptor.getValue();

        Assertions.assertEquals(accountExpected, paymentActual.getAccount());
    }

    @Test
    public void shouldReturnPaymentById() {

        Payment expected = new Payment();

        Mockito.when(paymentDAO.getPayment(1)).thenReturn(expected);

        Payment actual = paymentService.getPayment(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeletePaymentById() {

        Mockito.doNothing().when(paymentDAO).deletePaymentById(1);

        paymentService.deletePaymentById(1);

        Mockito.verify(paymentDAO, times(1)).deletePaymentById(1);
    }

    @Test
    public void shouldDeletePaymentByAccountId() {

        Mockito.doNothing().when(paymentDAO).deletePaymentByAccountId(1);

        paymentService.deletePaymentByAccountId(1);

        Mockito.verify(paymentDAO, times(1)).deletePaymentByAccountId(1);
    }
}