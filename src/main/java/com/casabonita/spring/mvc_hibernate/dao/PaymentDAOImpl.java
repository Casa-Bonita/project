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
        List<Payment> allPayments = session.createQuery("from Payment as p order by p.account asc, p.date asc", Payment.class).getResultList();

        return allPayments;
    }

    @Override
    public void savePayment(Payment payment) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(payment);

    }

    @Override
    public Payment getPayment(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Payment payment = session.get(Payment.class, id);

        return payment;
    }

    @Override
    public void deletePaymentById(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Payment> query = session.createQuery("delete from Payment where id=:param");
        query.setParameter("param", id);
        query.executeUpdate();

    }

    @Override
    public void deletePaymentByAccountId(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Payment> queryPayment = session.createQuery("delete from Payment where account.id=:param");
        queryPayment.setParameter("param", id);
        queryPayment.executeUpdate();

    }
}
