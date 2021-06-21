package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

public class RenterServiceImplTest {

    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
    ContractDAO contractDAO = Mockito.mock(ContractDAO.class);
    PaymentDAO paymentDAO = Mockito.mock(PaymentDAO.class);
    RenterDAO renterDAO = Mockito.mock(RenterDAO.class);

    RenterService renterService = new RenterServiceImpl(accountDAO, contractDAO, paymentDAO, renterDAO);

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

    @Test
    public void shouldSaveRenter(){

        Renter expected = new Renter();

        Mockito.doNothing().when(renterDAO).saveRenter(expected);

        renterService.saveRenter(expected);

        Mockito.verify(renterDAO, times(1)).saveRenter(expected);
    }

    @Test
    public void shouldReturnRenterById(){

        Renter expected = new Renter();

        Mockito.when(renterDAO.getRenter(1)).thenReturn(expected);

        Renter actual = renterService.getRenter(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRenterByName(){

        Renter expected = new Renter();

        String renterName = "TestName";

        Mockito.when(renterDAO.getRenterByName(renterName)).thenReturn(expected);

        Renter actual = renterService.getRenterByName(renterName);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteRenterById(){

        Mockito.doNothing().when(renterDAO).deleteRenterById(1);

        renterService.deleteRenterById(1);

        Mockito.verify(renterDAO, times(1)).deleteRenterById(1);
    }
}