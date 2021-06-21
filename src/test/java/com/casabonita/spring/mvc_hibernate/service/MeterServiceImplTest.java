package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class MeterServiceImplTest {

    MeterDAO meterDAO = Mockito.mock(MeterDAO.class);
    PlaceDAO placeDAO = Mockito.mock(PlaceDAO.class);
    ReadingDAO readingDAO = Mockito.mock(ReadingDAO.class);

    MeterService meterService = new MeterServiceImpl(meterDAO, placeDAO, readingDAO);

    @Test
    public void shouldReturnAllMeters() {

        List<Meter> expected = List.of(new Meter());

        Mockito.when(meterDAO.getAllMeters()).thenReturn(expected);

        List<Meter> actual = meterService.getAllMeters();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveMeter() {

        Meter meterExpected = new Meter();
        Place placeExpected = new Place();
        int meterPlaceNumber = 123;

        ArgumentCaptor<Meter> requestCaptor = ArgumentCaptor.forClass(Meter.class);
        Mockito.when(placeDAO.getPlaceByNumber(meterPlaceNumber)).thenReturn(placeExpected);

        meterService.saveMeter(meterExpected, meterPlaceNumber);

        Mockito.verify(meterDAO, times(1)).saveMeter(requestCaptor.capture());
        Meter meterActual = requestCaptor.getValue();

        Assertions.assertEquals(placeExpected, meterActual.getMeterPlace());
    }

    @Test
    public void shouldReturnMeterById() {

        Meter expected = new Meter();

        Mockito.when(meterDAO.getMeter(1)).thenReturn(expected);

        Meter actual = meterService.getMeter(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMeterByPlaceId() {

        Meter expected = new Meter();

        Mockito.when(meterDAO.getMeterByPlaceId(1)).thenReturn(expected);

        Meter actual = meterService.getMeterByPlaceId(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMeterByNumber() {

        Meter expected = new Meter();

        String meterNumber = "TestNumber";

        Mockito.when(meterDAO.getMeterByNumber(meterNumber)).thenReturn(expected);

        Meter actual = meterService.getMeterByNumber(meterNumber);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteMeterById() {

        Mockito.doNothing().when(meterDAO).deleteMeterById(1);

        meterService.deleteMeterById(1);

        Mockito.verify(meterDAO, times(1)).deleteMeterById(1);
    }
}