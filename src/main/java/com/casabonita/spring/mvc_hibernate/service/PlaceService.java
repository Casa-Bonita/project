package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlaces();

    void savePlace(Place place);

    Place getPlace(int id);

    void deletePlace(int id);

    Place getPlaceByNumber(int number);
}
