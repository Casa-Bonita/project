//package com.casabonita.spring.mvc_hibernate.service;
//
//import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
//import com.casabonita.spring.mvc_hibernate.entity.Meter;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//
//import static org.mockito.Mockito.times;
//
//class MeterServiceImplTest {
//
//    MeterDAO meterDAO = Mockito.mock(MeterDAO.class);
//
//    MeterService meterService = new MeterServiceImpl(meterDAO);
//
//    @Test
//    public void shouldReturnAllMeters() {
//
//        List<Meter> expected = List.of(new Meter());
//
//        Mockito.when(meterDAO.getAllMeters()).thenReturn(expected);
//
//        List<Meter> actual = meterService.getAllMeters();
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    // TODO
//    @Test
//    public void shouldSaveMeter() {
//
//        Meter expected = new Meter();
//
//        Mockito.doNothing().when(meterDAO).saveMeter(expected);
//
//        meterService.saveMeter(expected);
//
//        Mockito.verify(meterDAO, times(1)).saveMeter(expected);
//
//    }
//
//    @Test
//    public void shouldReturnMeterById() {
//
//        Meter expected = new Meter();
//
//        Mockito.when(meterDAO.getMeter(1)).thenReturn(expected);
//
//        Meter actual = meterService.getMeter(1);
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void shouldDeleteMeter() {
//
//        Mockito.doNothing().when(meterDAO).deleteMeter(1);
//
//        meterService.deleteMeter(1);
//
//        Mockito.verify(meterDAO, times(1)).deleteMeter(1);
//
//    }
//}