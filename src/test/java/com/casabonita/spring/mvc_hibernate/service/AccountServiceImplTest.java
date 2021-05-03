package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);

    AccountService accountService = new AccountServiceImpl(accountDAO);

    @Test
    void shoulReturnAllAccounts() {

        List<Account> expected = List.of(new Account());
        Mockito.when(accountDAO.getAllAccounts()).thenReturn(expected);

        List<Account> actual = accountService.getAllAccounts();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSaveAccount() {

        Account expected = new Account();

        accountDAO.saveAccount(expected);

        Mockito.when(accountService.getAccount(1)).thenReturn(expected);

        Account actual = accountService.getAccount(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnAccount() {

        Account expected = new Account();
        Mockito.when(accountDAO.getAccount(1)).thenReturn(expected);

        Account actual = accountService.getAccount(1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteAccount() {

        List<Account> expected = List.of(new Account());

        accountDAO.deleteAccount(1);

        Mockito.when(accountService.getAllAccounts()).thenReturn(expected);

        List<Account> actual = accountService.getAllAccounts();

        Assertions.assertEquals(expected, actual);
    }
}