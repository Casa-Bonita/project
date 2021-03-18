package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDAOImpl implements PlaceDAO{

    @Autowired
    SessionFactory sessionFactory;

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
        Query<Place> query = session.createQuery("delete from Place where id=:placeId");
        query.setParameter("placeId", id);
        query.executeUpdate();
    }
}
