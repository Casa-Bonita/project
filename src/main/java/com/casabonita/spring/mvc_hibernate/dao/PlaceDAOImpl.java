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
public class PlaceDAOImpl implements PlaceDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Place> getAllPlaces() {

        Session session = sessionFactory.getCurrentSession();
        List<Place> allPlaces = session.createQuery("from Place as p order by p.name asc", Place.class).getResultList();

        return allPlaces;
    }

    @Override
    public void savePlace(Place place) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(place);

    }

    @Override
    public Place getPlaceById(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        Place place = session.get(Place.class, id);

        return place;
    }

    @Override
    public Place getPlaceByNumber(int number) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(" from Place where number=:param");
        query.setParameter("param", number);

        Place place = (Place) query.getSingleResult();

        return place;
    }

    @Override
    public void deletePlaceById(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Place> queryPlace = session.createQuery("delete from Place where id=:param");
        queryPlace.setParameter("param", id);
        queryPlace.executeUpdate();
    }
}
