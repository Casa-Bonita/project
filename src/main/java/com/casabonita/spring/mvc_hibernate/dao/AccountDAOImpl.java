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
    public Account getAccount(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, id);

        return account;
    }

    @Override
    public Account getAccountByContractId(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Account where accountContract.id=:param");
        query.setParameter("param", id);

        List results = query.getResultList();
        if (results.isEmpty()) {
            return null; // no-results case
        } else {
            return (Account) results.get(0);
        }
    }

    @Override
    public Account getAccountByNumber(String number) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(" from Account where number=:param");
        query.setParameter("param", number);

        Account account = (Account) query.getSingleResult();

        return account;
    }

    @Override
    public void deleteAccountById(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Account> queryAccount = session.createQuery("delete from Account where id=:param");
        queryAccount.setParameter("param", id);
        queryAccount.executeUpdate();
    }
}
