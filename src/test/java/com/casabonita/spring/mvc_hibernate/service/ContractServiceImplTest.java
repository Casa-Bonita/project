package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.*;
import com.casabonita.spring.mvc_hibernate.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class ContractServiceImplTest {

    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
    ContractDAO contractDAO = Mockito.mock(ContractDAO.class);
    PaymentDAO paymentDAO = Mockito.mock(PaymentDAO.class);
    PlaceDAO placeDAO = Mockito.mock(PlaceDAO.class);
    RenterDAO renterDAO = Mockito.mock(RenterDAO.class);

    ContractService contractService = new ContractServiceImpl(accountDAO, contractDAO, paymentDAO, placeDAO, renterDAO);

    @Test
    public void shouldReturnAllContracts() {

        List<Contract> expected = List.of(new Contract());

        Mockito.when(contractDAO.getAllContracts()).thenReturn(expected);

        List<Contract> actual = contractService.getAllContracts();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveContract() {

        Contract contractExpected = new Contract();
        Place placeExpected = new Place();
        Renter renterExpected = new Renter();
        int contractPlaceNumber = 123;
        String contractRenterName = "TestName";

        ArgumentCaptor<Contract> requestCaptor = ArgumentCaptor.forClass(Contract.class);
        Mockito.when(placeDAO.getPlaceByNumber(123)).thenReturn(placeExpected);

        Mockito.when(renterDAO.getRenterByName(contractRenterName)).thenReturn(renterExpected);

        contractService.saveContract(contractExpected, contractPlaceNumber, contractRenterName);

        Mockito.verify(contractDAO, times(1)).saveContract(requestCaptor.capture());
        Contract contractActual = requestCaptor.getValue();

        Assertions.assertEquals(placeExpected, contractActual.getContractPlace());
        Assertions.assertEquals(renterExpected, contractActual.getRenter());
    }

    @Test
    public void shouldReturnContractById() {

        Contract expected = new Contract();

        Mockito.when(contractDAO.getContract(1)).thenReturn(expected);

        Contract actual = contractService.getContract(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnContractByPlaceId () {

        Contract expected = new Contract();

        Mockito.when(contractDAO.getContractByPlaceId (1)).thenReturn(expected);

        Contract actual = contractService.getContractByPlaceId (1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnContractByRenterId() {

        List<Contract> expected = List.of(new Contract());

        Mockito.when(contractDAO.getContractByRenterId(1)).thenReturn(expected);

        List<Contract> actual = contractService.getContractByRenterId(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnContractByNumber() {

        Contract expected = new Contract();

        String contractNumber = "TestNumber";

        Mockito.when(contractDAO.getContractByNumber (contractNumber)).thenReturn(expected);

        Contract actual = contractService.getContractByNumber (contractNumber);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteContractById() {

        Account accountExpected = new Account();
        int accountId = 1;

        Mockito.when(accountDAO.getAccountByContractId(accountId)).thenReturn(accountExpected);

        Mockito.doNothing().when(contractDAO).deleteContractById(1);

        contractService.deleteContractById(1);

        Mockito.verify(contractDAO, times(1)).deleteContractById(1);
    }
}