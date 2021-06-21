package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class AccountServiceImplTest {

    AccountDAO accountDAO = mock(AccountDAO.class);
    ContractDAO contractDAO = mock(ContractDAO.class);
    PaymentDAO paymentDAO = mock(PaymentDAO.class);

    AccountService accountService = new AccountServiceImpl(accountDAO, contractDAO, paymentDAO);

    @Test
    public void shouldReturnAllAccounts() {

        List<Account> expected = List.of(new Account());

        Mockito.when(accountDAO.getAllAccounts()).thenReturn(expected);

        List<Account> actual = accountService.getAllAccounts();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveAccount() {

        Account accountExpected = new Account();
        Contract contractExpected = new Contract();
        String contractNumber = "TestNumber";
        contractExpected.setNumber(contractNumber);

        ArgumentCaptor<Account> requestCaptor = ArgumentCaptor.forClass(Account.class);
        Mockito.when(contractDAO.getContractByNumber(contractNumber)).thenReturn(contractExpected);

        accountService.saveAccount(accountExpected, contractNumber);

        Mockito.verify(accountDAO, times(1)).saveAccount(requestCaptor.capture());
        Account accountActual = requestCaptor.getValue();

        Assertions.assertEquals(contractExpected, accountActual.getAccountContract());
    }

    @Test
    public void shouldReturnAccountById() {

        Account expected = new Account();

        Mockito.when(accountDAO.getAccount(1)).thenReturn(expected);

        Account actual = accountService.getAccount(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAccountByContractId() {

        Account expected = new Account();

        Mockito.when(accountDAO.getAccountByContractId(1)).thenReturn(expected);

        Account actual = accountService.getAccountByContractId(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAccountByNumber() {

        Account expected = new Account();

        String accountNumber = "TestNumber";

        Mockito.when(accountDAO.getAccountByNumber(accountNumber)).thenReturn(expected);

        Account actual = accountService.getAccountByNumber(accountNumber);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteAccountById() {

        Mockito.doNothing().when(accountDAO).deleteAccountById(1);

        accountService.deleteAccountById(1);

        Mockito.verify(accountDAO, times(1)).deleteAccountById(1);
    }
}