package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> getAllAccounts();

    void saveAccount(Account account);

    Account getAccount(int id);

    void deleteAccount(int id);

    Account getAccountByNumber(String number);
}
