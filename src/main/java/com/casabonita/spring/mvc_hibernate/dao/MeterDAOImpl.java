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
public class MeterDAOImpl implements MeterDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Meter> getAllMeters() {

        Session session = sessionFactory.getCurrentSession();
        List<Meter> allMeters = session.createQuery("from Meter as m order by m.number asc", Meter.class).getResultList();

        return allMeters;
    }

    @Override
    public void saveMeter(Meter meter) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(meter);
    }

    @Override
    public Meter getMeter(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Meter meter = session.get(Meter.class, id);

        return meter;
    }

    @Override
    public Meter getMeterByPlaceId(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Meter where meterPlace.id=:param");
        query.setParameter("param", id);

        List results = query.getResultList();
        if (results.isEmpty()) {
            return null; // no-results case
        } else {
            return (Meter) results.get(0);
        }
    }

    @Override
    public Meter getMeterByNumber(String number) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(" from Meter where number=:param");
        query.setParameter("param", number);

        Meter meter = (Meter) query.getSingleResult();

        return meter;
    }

    @Override
    public void deleteMeterById(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Meter> queryMeter = session.createQuery("delete from Meter where id=:param");
        queryMeter.setParameter("param", id);
        queryMeter.executeUpdate();
    }
}
