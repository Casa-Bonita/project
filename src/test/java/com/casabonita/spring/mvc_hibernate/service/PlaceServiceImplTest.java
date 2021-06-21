package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.*;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class PlaceServiceImplTest {

    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
    ContractDAO contractDAO = Mockito.mock(ContractDAO.class);
    MeterDAO meterDAO = Mockito.mock(MeterDAO.class);
    PaymentDAO paymentDAO = Mockito.mock(PaymentDAO.class);
    PlaceDAO placeDAO = Mockito.mock(PlaceDAO.class);
    ReadingDAO readingDAO = Mockito.mock(ReadingDAO.class);

    PlaceService placeService = new PlaceServiceImpl(accountDAO, contractDAO, meterDAO, paymentDAO, placeDAO, readingDAO);

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

        Mockito.when(placeDAO.getPlaceById(1)).thenReturn(expected);

        Place actual = placeService.getPlaceById(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnPlaceByNumber() {

        Place expected = new Place();

        int placeNumber = 123;

        Mockito.when(placeDAO.getPlaceByNumber(placeNumber)).thenReturn(expected);

        Place actual = placeService.getPlaceByNumber(placeNumber);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeletePlaceById() {

        Mockito.doNothing().when(placeDAO).deletePlaceById(1);

        placeService.deletePlaceById(1);

        Mockito.verify(placeDAO, times(1)).deletePlaceById(1);
    }
}