package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Place;

import java.util.List;

public interface PlaceService {

    public List<Place> getAllPlaces();

    public void savePlace(Place place);

    public Place getPlace(int id);

    public void deletePlace(int id);
}
