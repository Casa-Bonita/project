package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

class ReadingServiceImplTest {

    ReadingDAO readingDAO = Mockito.mock(ReadingDAO.class);

    ReadingService readingService = new ReadingServiceImpl(readingDAO);

    @Test
    void shouldReturnAllReadings() {

        List<Reading> expected = List.of(new Reading());
        Mockito.when(readingDAO.getAllReadings()).thenReturn(expected);

        List<Reading> actual = readingService.getAllReadings();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void shouldSaveReading() {

        Reading expected = new Reading();
        readingDAO.saveReading(expected);

        Mockito.when(readingService.getReading(1)).thenReturn(expected);

        Reading actual = readingService.getReading(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnReading() {

        Reading expected = new Reading();

        Mockito.when(readingDAO.getReading(1)).thenReturn(expected);

        Reading actual = readingService.getReading(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteReading() {

        List<Reading> expected = List.of(new Reading());
        readingDAO.deleteReading(1);

        Mockito.when(readingService.getAllReadings()).thenReturn(expected);

        List<Reading> actual = readingService.getAllReadings();

        Assertions.assertEquals(expected, actual);
    }
}