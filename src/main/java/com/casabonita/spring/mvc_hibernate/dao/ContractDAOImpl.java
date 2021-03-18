package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractDAOImpl implements ContractDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Contract> getAllContracts() {

        Session session = sessionFactory.getCurrentSession();
        List<Contract> allContracts = session.createQuery("from Contract", Contract.class).getResultList();

        return allContracts;
    }

    @Override
    public void saveContract(Contract contract) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contract);
    }

    @Override
    public Contract getContract(int id) {

        Session session = sessionFactory.getCurrentSession();
        Contract contract = session.get(Contract.class, id);

        return contract;
    }

    @Override
    public void deleteContract(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query<Contract> query = session.createQuery("delete from Contract where id=:contractId");
        query.setParameter("contractId", id);
        query.executeUpdate();

    }
}
