package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractServiceImplTest {

    ContractDAO contractDAO = Mockito.mock(ContractDAO.class);

    ContractService contractService = new ContractServiceImpl(contractDAO);

    @Test
    void shouldReturnAllContracts() {

        List<Contract> expected = List.of(new Contract());
        Mockito.when(contractDAO.getAllContracts()).thenReturn(expected);

        List<Contract> actual = contractService.getAllContracts();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSaveContract() {

        Contract expected = new Contract();

        contractDAO.saveContract(expected);

        Mockito.when(contractService.getContract(1)).thenReturn(expected);

        Contract actual = contractService.getContract(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnContract() {

        Contract expected = new Contract();
        Mockito.when(contractDAO.getContract(1)).thenReturn(expected);

        Contract actual = contractService.getContract(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteContract() {

        List<Contract> expected = List.of(new Contract());

        contractDAO.deleteContract(1);

        Mockito.when(contractService.getAllContracts()).thenReturn(expected);

        List<Contract> actual = contractService.getAllContracts();

        Assertions.assertEquals(expected, actual);
    }
}