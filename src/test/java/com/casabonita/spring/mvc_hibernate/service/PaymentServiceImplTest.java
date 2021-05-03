package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {

    PaymentDAO paymentDAO = Mockito.mock(PaymentDAO.class);

    PaymentService paymentService = new PaymentServiceImpl(paymentDAO);

    @Test
    void shoudReturnAllPayments() {

        List<Payment> expected = List.of(new Payment());
        Mockito.when(paymentDAO.getAllPayments()).thenReturn(expected);

        List<Payment> actual = paymentService.getAllPayments();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSavePayment() {

        Payment expected = new Payment();

        paymentDAO.savePayment(expected);

        Mockito.when(paymentService.getPayment(1)).thenReturn(expected);

        Payment actual = paymentService.getPayment(1);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldReturnPayment() {

        Payment expected = new Payment();
        Mockito.when(paymentDAO.getPayment(1)).thenReturn(expected);

        Payment actual = paymentService.getPayment(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeletePayment() {

        List<Payment> expected = List.of(new Payment());

        paymentDAO.deletePayment(1);
        Mockito.when(paymentService.getAllPayments()).thenReturn(expected);

        List<Payment> actual = paymentService.getAllPayments();

        Assertions.assertEquals(expected, actual);
    }
}