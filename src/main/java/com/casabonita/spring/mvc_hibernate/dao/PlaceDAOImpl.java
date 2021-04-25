package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PlaceDAOImpl implements PlaceDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Place> getAllPlaces() {

        Session session = sessionFactory.getCurrentSession();
        List<Place> allPlaces = session.createQuery("from Place", Place.class).getResultList();

        return allPlaces;
    }

    @Override
    public void savePlace(Place place) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(place);

    }

    @Override
    public Place getPlace(int id) {

        Session session = sessionFactory.getCurrentSession();
        Place place = session.get(Place.class, id);

        return place;
    }

    @Override
    public void deletePlace(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Reading> queryReading = session.createQuery("delete from Reading where meter.id=:param1");
        queryReading.setParameter("param1", id);
        queryReading.executeUpdate();

        Query<Meter> queryMeter = session.createQuery("delete from Meter where meterPlace.id=:param2");
        queryMeter.setParameter("param2", id);
        queryMeter.executeUpdate();

        Query<Contract> queryContract = session.createQuery("delete from Contract where contractPlace.id=:param3");
        queryContract.setParameter("param3", id);
        queryContract.executeUpdate();

        Query<Place> queryPlace = session.createQuery("delete from Place where id=:param4");
        queryPlace.setParameter("param4", id);
        queryPlace.executeUpdate();
    }
}
