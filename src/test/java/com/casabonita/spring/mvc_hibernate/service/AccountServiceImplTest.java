package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

class AccountServiceImplTest {

    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);

    AccountService accountService = new AccountServiceImpl(accountDAO);

    @Test
    public void shouldReturnAllAccounts() {

        List<Account> expected = List.of(new Account());

        Mockito.when(accountDAO.getAllAccounts()).thenReturn(expected);

        List<Account> actual = accountService.getAllAccounts();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveAccount() {

        Account expected = new Account();

        Mockito.doNothing().when(accountDAO).saveAccount(expected);

        accountService.saveAccount(expected);

        Mockito.verify(accountDAO, times(1)).saveAccount(expected);

    }

    @Test
    public void shouldReturnAccountById() {

        Account expected = new Account();

        Mockito.when(accountDAO.getAccount(1)).thenReturn(expected);

        Account actual = accountService.getAccount(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteAccount() {

        Mockito.doNothing().when(accountDAO).deleteAccount(1);

        accountService.deleteAccount(1);

        Mockito.verify(accountDAO, times(1)).deleteAccount(1);

    }
}