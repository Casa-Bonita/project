package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

public class RenterServiceImplTest {

    RenterDAO renterDAO = Mockito.mock(RenterDAO.class);

    RenterService renterService = new RenterServiceImpl(renterDAO);

    @Test
    public void shouldReturnAllRenters() {

        // input data
        List<Renter> expected = List.of(new Renter());
        Mockito.when(renterDAO.getAllRenters()).thenReturn(expected);

        // call method that will be tested
        List<Renter> actual = renterService.getAllRenters();

        // output data
        Assertions.assertEquals(expected, actual);

    }

    // тестирование с использованием связки save -> get
    @Test
    public void shouldSaveRenter(){

        Renter expected = new Renter();
        renterDAO.saveRenter(expected);

        Mockito.when(renterService.getRenter(1)).thenReturn(expected);

        Renter actual = renterService.getRenter(1);

        Assertions.assertEquals(expected, actual);

    }

    // тестирование с использованием связки doNothing -> Verify
    @Test
    public void shouldSaveRenter2(){

        Renter expected = new Renter();

        Mockito.doNothing().when(renterDAO).saveRenter(expected);

        renterDAO.saveRenter(expected);

        Mockito.verify(renterDAO, times(1)).saveRenter(expected);

    }

    @Test
    public void shouldReturnRenterById(){

        Renter expected = new Renter();
        Mockito.when(renterDAO.getRenter(1)).thenReturn(expected);

        Renter actual = renterService.getRenter(1);

        Assertions.assertEquals(expected, actual);
    }

    // тестирование с использованием связки delete -> getAll
    @Test
    public void shouldDeleteRenter(){

        List<Renter> expected = List.of(new Renter());
        renterDAO.deleteRenter(1);

        Mockito.when(renterService.getAllRenters()).thenReturn(expected);

        List<Renter> actual = renterService.getAllRenters();

        Assertions.assertEquals(expected, actual);

    }

    // тестирование с использованием связки doNothing -> Verify
    @Test
    public void shouldDeleteRenter2(){

        Mockito.doNothing().when(renterDAO).deleteRenter(1);

        renterDAO.deleteRenter(1);

        Mockito.verify(renterDAO, times(1)).deleteRenter(1);

    }

}