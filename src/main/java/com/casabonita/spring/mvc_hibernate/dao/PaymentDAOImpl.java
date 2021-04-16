package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PaymentDAOImpl implements PaymentDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Payment> getAllPayments() {

        Session session = sessionFactory.getCurrentSession();
        List<Payment> allPayments = session.createQuery("from Payment", Payment.class).getResultList();

        return allPayments;
    }

    @Override
    public void savePayment(Payment payment) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(payment);

    }

    @Override
    public Payment getPayment(int id) {

        Session session = sessionFactory.getCurrentSession();
        Payment payment = session.get(Payment.class, id);

        return payment;
    }

    @Override
    public void deletePayment(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Payment> query = session.createQuery("delete from Payment where id=:paymentId");
        query.setParameter("paymentId", id);
        query.executeUpdate();

    }
}
