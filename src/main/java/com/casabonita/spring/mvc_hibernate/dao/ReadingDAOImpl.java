package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.MeterData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReadingDAOImpl implements ReadingDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MeterData> getAllReadings() {

        Session session = sessionFactory.getCurrentSession();
        List<MeterData> allMeterDatas = session.createQuery("from MeterData", MeterData.class).getResultList();

        return allMeterDatas;
    }

    @Override
    public void saveReading(MeterData meterData) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(meterData);

    }

    @Override
    public MeterData getReading(int id) {

        Session session = sessionFactory.getCurrentSession();
        MeterData meterData = session.get(MeterData.class, id);

        return meterData;
    }

    @Override
    public void deleteReading(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query<MeterData> query = session.createQuery("delete from MeterData where id=:readingId");
        query.setParameter("readingId", id);
        query.executeUpdate();

    }
}
