//package com.casabonita.spring.mvc_hibernate.service;
//
//import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
//import com.casabonita.spring.mvc_hibernate.entity.Payment;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//
//import static org.mockito.Mockito.times;
//
//class PaymentServiceImplTest {
//
//    PaymentDAO paymentDAO = Mockito.mock(PaymentDAO.class);
//
//    PaymentService paymentService = new PaymentServiceImpl(paymentDAO);
//
//    @Test
//    public void shouldReturnAllPayments() {
//
//        List<Payment> expected = List.of(new Payment());
//
//        Mockito.when(paymentDAO.getAllPayments()).thenReturn(expected);
//
//        List<Payment> actual = paymentService.getAllPayments();
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    // TODO
//    @Test
//    public void shouldSavePayment() {
//
//        Payment expected = new Payment();
//
//        Mockito.doNothing().when(paymentDAO).savePayment(expected);
//
//        paymentService.savePayment(expected);
//
//        Mockito.verify(paymentDAO, times(1)).savePayment(expected);
//
//    }
//
//    @Test
//    public void shouldReturnPaymentById() {
//
//        Payment expected = new Payment();
//
//        Mockito.when(paymentDAO.getPayment(1)).thenReturn(expected);
//
//        Payment actual = paymentService.getPayment(1);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void shouldDeletePayment() {
//
//        Mockito.doNothing().when(paymentDAO).deletePayment(1);
//
//        paymentService.deletePayment(1);
//
//        Mockito.verify(paymentDAO, times(1)).deletePayment(1);
//
//    }
//}