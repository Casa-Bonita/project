package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.List;

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

}