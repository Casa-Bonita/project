package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Place;

import java.util.List;

public interface PlaceDAO {

    public List<Place> getAllPlaces();

    public void savePlace (Place place);

    public Place getPlace(int id);

    public void deletePlace(int id);
}
