package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{

//    @Autowired
    private final PlaceDAO placeDAO;

    public PlaceServiceImpl(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    @Override
    @Transactional
    public List<Place> getAllPlaces() {

        return placeDAO.getAllPlaces();
    }

    @Override
    @Transactional
    public void savePlace(Place place) {

        placeDAO.savePlace(place);
    }

    @Override
    @Transactional
    public Place getPlace(int id) {

        return placeDAO.getPlace(id);
    }

    @Override
    @Transactional
    public void deletePlace(int id) {

        placeDAO.deletePlace(id);
    }

    @Override
    @Transactional
    public Place getPlaceByNumber(int number) {

        return placeDAO.getPlace(number);
    }
}
