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
public class ContractDAOImpl implements ContractDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Contract> getAllContracts() {

        Session session = sessionFactory.getCurrentSession();
        List<Contract> allContracts = session.createQuery("from Contract as c order by c.number asc", Contract.class).getResultList();

        return allContracts;
    }

    @Override
    public void saveContract(Contract contract) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contract);
    }

    @Override
    public Contract getContract(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Contract contract = session.get(Contract.class, id);

        return contract;
    }

    @Override
    public Contract getContractByPlaceId(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where contractPlace.id=:param");
        query.setParameter("param", id);

        List results = query.getResultList();
        if (results.isEmpty()) {
            return null; // no-results case
        } else {
            return (Contract) results.get(0);
        }
    }

    @Override
    public List<Contract> getContractByRenterId(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where renter.id=:param");
        query.setParameter("param", id);

        List<Contract> contractList = query.getResultList();

        return contractList;
    }

    @Override
    public Contract getContractByNumber(String number) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Contract where number=:param");
        query.setParameter("param", number);

        Contract contract = (Contract) query.getSingleResult();

        return contract;
    }

    @Override
    public void deleteContractById(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Contract> query = session.createQuery("delete from Contract where id=:param");
        query.setParameter("param", id);
        query.executeUpdate();
    }
}
