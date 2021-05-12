//package com.casabonita.spring.mvc_hibernate.service;
//
//import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
//import com.casabonita.spring.mvc_hibernate.entity.Contract;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//
//import static org.mockito.Mockito.times;
//
//class ContractServiceImplTest {
//
//    ContractDAO contractDAO = Mockito.mock(ContractDAO.class);
//
//    ContractService contractService = new ContractServiceImpl(contractDAO);
//
//    @Test
//    public void shouldReturnAllContracts() {
//
//        List<Contract> expected = List.of(new Contract());
//
//        Mockito.when(contractDAO.getAllContracts()).thenReturn(expected);
//
//        List<Contract> actual = contractService.getAllContracts();
//
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//    // TODO
//    @Test
//    public void shouldSaveContract() {
//
//        Contract expected = new Contract();
//
//        Mockito.doNothing().when(contractDAO).saveContract(expected);
//
//        contractService.saveContract(expected);
//
//        Mockito.verify(contractDAO, times(1)).saveContract(expected);
//
//    }
//
//    @Test
//    public void shouldReturnContractById() {
//
//        Contract expected = new Contract();
//
//        Mockito.when(contractDAO.getContract(1)).thenReturn(expected);
//
//        Contract actual = contractService.getContract(1);
//
//        Assertions.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void shouldDeleteContract() {
//
//        Mockito.doNothing().when(contractDAO).deleteContract(1);
//
//        contractService.deleteContract(1);
//
//        Mockito.verify(contractDAO, times(1)).deleteContract(1);
//
//    }
//}