package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaceServiceImplTest {

    PlaceDAO placeDAO = Mockito.mock(PlaceDAO.class);

    PlaceService placeService = new PlaceServiceImpl(placeDAO);

    @Test
    void shouldReturnAllPlaces() {

        List<Place> expected = List.of(new Place());
        Mockito.when(placeDAO.getAllPlaces()).thenReturn(expected);

        List<Place> actual = placeService.getAllPlaces();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSavePlace() {

        Place expected = new Place();

        placeDAO.savePlace(expected);

        Mockito.when(placeService.getPlace(1)).thenReturn(expected);

        Place actual = placeService.getPlace(1);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldReturnPlace() {

        Place expected = new Place();
        Mockito.when(placeDAO.getPlace(1)).thenReturn(expected);

        Place actual = placeService.getPlace(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeletePlace() {

        List<Place> expected = List.of(new Place());

        placeDAO.deletePlace(1);

        Mockito.when(placeService.getAllPlaces()).thenReturn(expected);

        List<Place> actual = placeService.getAllPlaces();

        Assertions.assertEquals(expected, actual);
    }
}