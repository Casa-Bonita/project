package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.MeterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeterServiceImplTest {

    MeterDAO meterDAO = Mockito.mock(MeterDAO.class);

    MeterService meterService = new MeterServiceImpl(meterDAO);

    @Test
    void shoulReturnAllMeters() {

        List<Meter> expected = List.of(new Meter());
        Mockito.when(meterDAO.getAllMeters()).thenReturn(expected);

        List<Meter> actual = meterService.getAllMeters();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSaveMeter() {

        Meter expected = new Meter();
        meterDAO.saveMeter(expected);

        Mockito.when(meterService.getMeter(1)).thenReturn(expected);

        Meter actual = meterService.getMeter(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMeter() {

        Meter expected = new Meter();
        Mockito.when(meterDAO.getMeter(1)).thenReturn(expected);

        Meter actual = meterService.getMeter(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteMeter() {

        List<Meter> expected = List.of(new Meter());

        meterDAO.deleteMeter(1);

        Mockito.when(meterService.getAllMeters()).thenReturn(expected);

        List<Meter> actual = meterService.getAllMeters();

        Assertions.assertEquals(expected, actual);
    }
}