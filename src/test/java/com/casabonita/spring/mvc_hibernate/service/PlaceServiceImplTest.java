package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class PlaceServiceImplTest {

    PlaceDAO placeDAO = Mockito.mock(PlaceDAO.class);

    PlaceService placeService = new PlaceServiceImpl(placeDAO);

    @Test
    public void shouldReturnAllPlaces() {

        List<Place> expected = List.of(new Place());

        Mockito.when(placeDAO.getAllPlaces()).thenReturn(expected);

        List<Place> actual = placeService.getAllPlaces();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSavePlace() {

        Place expected = new Place();

        Mockito.doNothing().when(placeDAO).savePlace(expected);

        placeService.savePlace(expected);

        Mockito.verify(placeDAO, times(1)).savePlace(expected);

    }

    @Test
    public void shouldReturnPlaceById() {

        Place expected = new Place();

        Mockito.when(placeDAO.getPlace(1)).thenReturn(expected);

        Place actual = placeService.getPlace(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeletePlace() {

        Mockito.doNothing().when(placeDAO).deletePlace(1);

        placeService.deletePlace(1);

        Mockito.verify(placeDAO, times(1)).deletePlace(1);

    }
}