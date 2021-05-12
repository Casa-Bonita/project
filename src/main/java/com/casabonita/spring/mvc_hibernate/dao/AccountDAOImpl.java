package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Account> getAllAccounts() {

        Session session = sessionFactory.getCurrentSession();
        List<Account> allAccounts = session.createQuery("from Account as a order by a.number asc", Account.class).getResultList();

        return allAccounts;
    }

    @Override
    public void saveAccount(Account account) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
    }

    @Override
    public Account getAccount(int id) {

        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, id);

        return account;
    }

    @Override
    public void deleteAccount(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Payment> queryPayment = session.createQuery("delete from Payment where account.id=:param1");
        queryPayment.setParameter("param1", id);
        queryPayment.executeUpdate();

        Query<Account> queryAccount = session.createQuery("delete from Account where id=:param2");
        queryAccount.setParameter("param2", id);
        queryAccount.executeUpdate();
    }

    @Override
    public Account getAccountByNumber(String number) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(" FROM Account WHERE number=:parameter");
        query.setParameter("parameter", number);

        Account account = (Account) query.getSingleResult();

        return account;
    }
}
