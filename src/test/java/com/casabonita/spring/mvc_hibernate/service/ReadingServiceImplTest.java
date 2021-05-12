//package com.casabonita.spring.mvc_hibernate.service;
//
//import com.casabonita.spring.mvc_hibernate.dao.ReadingDAO;
//import com.casabonita.spring.mvc_hibernate.entity.Reading;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//
//import static org.mockito.Mockito.times;
//
//class ReadingServiceImplTest {
//
//    ReadingDAO readingDAO = Mockito.mock(ReadingDAO.class);
//
//    ReadingService readingService = new ReadingServiceImpl(readingDAO);
//
//    @Test
//    public void shouldReturnAllReadings() {
//
//        List<Reading> expected = List.of(new Reading());
//
//        Mockito.when(readingDAO.getAllReadings()).thenReturn(expected);
//
//        List<Reading> actual = readingService.getAllReadings();
//
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//    // TODO
//    @Test
//    public void shouldSaveReading() {
//
//        Reading expected = new Reading();
//
//        Mockito.doNothing().when(readingDAO).saveReading(expected);
//
//        readingService.saveReading(expected);
//
//        Mockito.verify(readingDAO, times(1)).saveReading(expected);
//
//    }
//
//    @Test
//    public void shouldReturnReadingById() {
//
//        Reading expected = new Reading();
//
//        Mockito.when(readingDAO.getReading(1)).thenReturn(expected);
//
//        Reading actual = readingService.getReading(1);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void shouldDeleteReading() {
//
//        Mockito.doNothing().when(readingDAO).deleteReading(1);
//
//        readingService.deleteReading(1);
//
//        Mockito.verify(readingDAO, times(1)).deleteReading(1);
//
//    }
//}