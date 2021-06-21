package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Place;

import java.util.List;

public interface PlaceDAO {

    List<Place> getAllPlaces();

    void savePlace (Place place);

    Place getPlaceById(Integer id);

    Place getPlaceByNumber(int number);

    void deletePlaceById(Integer id);
}
