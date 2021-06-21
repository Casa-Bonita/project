package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class ReadingServiceImplTest {

    MeterDAO meterDAO = Mockito.mock(MeterDAO.class);
    ReadingDAO readingDAO = Mockito.mock(ReadingDAO.class);

    ReadingService readingService = new ReadingServiceImpl(meterDAO, readingDAO);

    @Test
    public void shouldReturnAllReadings() {

        List<Reading> expected = List.of(new Reading());

        Mockito.when(readingDAO.getAllReadings()).thenReturn(expected);

        List<Reading> actual = readingService.getAllReadings();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveReading() {

        Reading readingExpected = new Reading();
        Meter meterExpected = new Meter();
        String meterNumber = "TestMeter";
        meterExpected.setNumber(meterNumber);

        ArgumentCaptor<Reading> requestCaptor = ArgumentCaptor.forClass(Reading.class);
        Mockito.when(meterDAO.getMeterByNumber(meterNumber)).thenReturn(meterExpected);

        readingService.saveReading(readingExpected, meterNumber);

        Mockito.verify(readingDAO, times(1)).saveReading(requestCaptor.capture());
        Reading readingActual = requestCaptor.getValue();

        Assertions.assertEquals(meterExpected, readingActual.getMeter());
    }

    @Test
    public void shouldReturnReadingById() {

        Reading expected = new Reading();

        Mockito.when(readingDAO.getReading(1)).thenReturn(expected);

        Reading actual = readingService.getReading(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteReadingById() {

        Mockito.doNothing().when(readingDAO).deleteReadingById(1);

        readingService.deleteReadingById(1);

        Mockito.verify(readingDAO, times(1)).deleteReadingById(1);
    }

    @Test
    public void shouldDeleteReadingByMeterId() {

        Mockito.doNothing().when(readingDAO).deleteReadingByMeterId(1);

        readingService.deleteReadingByMeterId(1);

        Mockito.verify(readingDAO, times(1)).deleteReadingByMeterId(1);
    }
}